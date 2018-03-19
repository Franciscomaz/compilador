/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.scanner;

import compilador.token.TabelaDeTokens;
import compilador.token.Token;
import java.util.Stack;

/**
 *
 * @author Chicom
 */
public class Scanner {

    private final Leitor leitor;
    private final Stack<Token> bufferTokens;
    private final TabelaDeTokens tabelaDeTokens;

    public Scanner(Leitor leitor) {
        this.leitor = leitor;
        this.bufferTokens = new Stack<>();
        this.tabelaDeTokens = new TabelaDeTokens();
    }

    public Stack<Token> geTokens() throws ErroLexico {
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            if (Character.isWhitespace(caracter)) {
            } else if (Character.isLetter(caracter) || caracter=='_') {
                leitor.rollBack();
                bufferTokens.add(lerIdentificador());
            } else if (Character.isDigit(caracter)) {
                leitor.rollBack();
                bufferTokens.add(lerDigito());
            } else if (caracter.toString().matches("<|>|=")) {
                leitor.rollBack();
                bufferTokens.add(lerOperadorRelacional());
            } else if (caracter.toString().matches("$|;|,|\\.")) {
                leitor.rollBack();
                bufferTokens.add(lerCaracteresEspeciais());
            } else if (caracter == '\'') {
                bufferTokens.add(lerLiteral());
            } else if (caracter == ':'){
                leitor.rollBack();
                bufferTokens.add(lerAtribuidor());
            } else if (caracter.toString().matches("\\+|\\*|-|/")) {
                bufferTokens.add(new Token(tabelaDeTokens.getCodigo(caracter.toString()), caracter.toString()));
            } else if (caracter.toString().matches("\\(|\\)|\\[|\\]")) {
                bufferTokens.add(new Token(tabelaDeTokens.getCodigo(caracter.toString()), caracter.toString()));
            } else {
                throw new ErroLexico(leitor);
            }
        }
        return bufferTokens;
    }

    private Token lerIdentificador() throws ErroLexico {
        String lexema = "";
        while (leitor.hasNext()) {
            
            if(lexema.length() > 30){
                throw new ErroLexico(leitor);
            }
            
            Character caracter = leitor.proximoCaracter();
            
            if (!Character.isLetterOrDigit(caracter) && caracter != '_') {
                leitor.rollBack();
                break;
            }
            lexema += caracter;
        }
        return tabelaDeTokens.getIdentificador(lexema);
    }

    private Token lerDigito() throws ErroLexico {
        String lexema = "";
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            if (Character.isLetter(caracter) || caracter == '_') {
                throw new ErroLexico(leitor);
            }
            if (!Character.isDigit(caracter)) {
                leitor.rollBack();
                break;
            }
            
            lexema += caracter;
           
            //Trata se o numero for maior que a quantidade permitida...
            int valorInteiro = Integer.parseInt(lexema);
            if (valorInteiro > 32767 || valorInteiro < -32767) {
               throw new ErroLexico(leitor);
            }
            
        }
        return new Token(tabelaDeTokens.getCodigo("Inteiro"), lexema);
    }

    private Token lerOperadorRelacional() {
        Character caracter = leitor.proximoCaracter();
        String lexema = caracter.toString();
        if (caracter == '>' && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (caracter == '=') {
                lexema += caracter;
            } else {
                leitor.rollBack();
            }
        } else if (caracter == '<' && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (caracter == '=') {
                lexema += caracter;
            } else if (caracter == '>') {
                lexema += caracter;
            } else {
                leitor.rollBack();
            }
        }
        return new Token(tabelaDeTokens.getCodigo(lexema), lexema);
    }

    private Token lerCaracteresEspeciais() {
        Character caracter = leitor.proximoCaracter();
        String lexema = caracter.toString();
        if (!caracter.toString().matches("$|;|,") && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (caracter == '.') {
                lexema += caracter;
            } else {
                leitor.rollBack();
            }
        }
        return new Token(tabelaDeTokens.getCodigo(lexema), lexema);
    }

    private Token lerLiteral() throws ErroLexico {
        String lexema = "";
        Character caracter = null;
        while (leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if(lexema.length() > 255){
                throw new ErroLexico(leitor);
            }
            if (caracter == '\'') {
                break;
            }
            lexema += caracter;
        }
        if(caracter != '\''){
            throw new ErroLexico(leitor);
        }
        return new Token(tabelaDeTokens.getCodigo("Literal"), lexema);
    }
    
    private Token lerAtribuidor(){
        String lexema = leitor.proximoCaracter().toString();
        Character caracter = leitor.proximoCaracter();
        if(caracter != '='){
            leitor.rollBack();
        } else {
            lexema += caracter;
        }
        return new Token(tabelaDeTokens.getCodigo(lexema), lexema);
    }
}
