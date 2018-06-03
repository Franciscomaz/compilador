package compilador.lexico;

import compilador.token.Token;
import compilador.token.TokenFactory;

import java.util.Stack;

public class Scanner {

    private final Leitor leitor;
    private final Stack<Token> bufferTokens;

    public Scanner(Leitor leitor) {
        this.leitor = leitor;
        this.bufferTokens = new Stack<>();
    }

    public Stack<Token> analisar() throws ErroLexico {
        while (leitor.hasNext()) {
            final Character carater = leitor.proximoCarater();
            if (Character.isWhitespace(carater)) {
                continue;
            }
            if (Character.isLetter(carater) || carater == '_') {
                leitor.rollBack();
                bufferTokens.add(lerIdentificador());
            } else if (Character.isDigit(carater)) {
                leitor.rollBack();
                bufferTokens.add(lerDigito());
            } else if (carater.toString().matches("[<>=]")) {
                leitor.rollBack();
                bufferTokens.add(lerOperadorRelacional());
            } else if (carater.toString().matches("$|;|,|\\.")) {
                leitor.rollBack();
                bufferTokens.add(lerCaracteresEspeciais());
            } else if (carater == '\'') {
                bufferTokens.add(lerLiteral());
            } else if (carater == ':') {
                leitor.rollBack();
                bufferTokens.add(lerAtribuidor());
            } else if (carater.toString().matches("[+*-]")) {
                leitor.rollBack();
                bufferTokens.add(lerOperadorAritmetico());
            } else if (carater.toString().matches("[()\\[\\]]")) {
                leitor.rollBack();
                lerCaracteresDeAberturaOuFechamento();
            } else {
                throw new ErroLexico(leitor.posicao(), "Caráter desconhecido '" + carater + "'.");
            }
        }
        return bufferTokens;
    }

    private Token lerIdentificador() throws ErroLexico {
        var lexema = new StringBuilder();
        while (leitor.hasNext()) {
            if (lexema.length() > 30) {
                throw new ErroLexico(leitor.posicao(), "Quantidade de caracteres superior ao permitido[30].");
            }
            Character carater = leitor.proximoCarater();
            if (!Character.isLetterOrDigit(carater) && carater != '_') {
                leitor.rollBack();
                break;
            }
            lexema.append(carater);
        }
        return TokenFactory.criarIdentificador(lexema.toString(), leitor.posicao());
    }

    private Token lerDigito() throws ErroLexico {
        var lexema = new StringBuilder();
        while (leitor.hasNext()) {
            Character carater = leitor.proximoCarater();
            if (Character.isLetter(carater) || carater == '_') {
                throw new ErroLexico(leitor.posicao(), "Caráter inválido '" + carater + "'.");
            }
            if (!Character.isDigit(carater)) {
                leitor.rollBack();
                break;
            }
            lexema.append(carater);
        }
        return TokenFactory.criarInteiro(lexema.toString(), leitor.posicao());
    }

    private Token lerOperadorRelacional() {
        var lexema = new StringBuilder(leitor.proximoCarater().toString());
        if (lexema.charAt(0) == '>' && leitor.hasNext()) {
            Character carater = leitor.proximoCarater();
            if (carater == '=') {
                lexema.append(carater);
            } else {
                leitor.rollBack();
            }
        } else if (lexema.charAt(0) == '<' && leitor.hasNext()) {
            Character carater = leitor.proximoCarater();
            switch (carater) {
                case '=':
                    lexema.append(carater);
                    break;
                case '>':
                    lexema.append(carater);
                    break;
                default:
                    leitor.rollBack();
                    break;
            }
        }
        return TokenFactory.criar(lexema.toString(), leitor.posicao());
    }

    private Token lerCaracteresEspeciais() {
        String lexema = leitor.proximoCarater().toString();
        if (lexema.charAt(0) == '.' && leitor.hasNext()) {
            Character caracter = leitor.proximoCarater();
            if (caracter == '.') {
                lexema += caracter;
            } else {
                leitor.rollBack();
            }
        }
        return TokenFactory.criar(lexema, leitor.posicao());
    }

    private Token lerLiteral() throws ErroLexico {
        var lexema = new StringBuilder();
        Character carater = null;
        while (leitor.hasNext()) {
            carater = leitor.proximoCarater();
            if (lexema.length() > 255) {
                throw new ErroLexico(leitor.posicao(), "Quantidade de caracteres superior ao permitido[255].");
            }
            if (carater == '\'') {
                break;
            }
            lexema.append(carater);
        }
        if (carater != '\'') {
            throw new ErroLexico(leitor.posicao(), "Esperado caráter de fechamento '\''.");
        }
        return TokenFactory.criarLiteral(lexema.toString(), leitor.posicao());
    }

    private Token lerAtribuidor() {
        var lexema = new StringBuilder(leitor.proximoCarater().toString());
        Character carater = leitor.proximoCarater();
        if (carater != '=') {
            leitor.rollBack();
        } else {
            lexema.append(carater);
        }
        return TokenFactory.criar(lexema.toString(), leitor.posicao());
    }

    private Token lerOperadorAritmetico() throws ErroLexico {
        String lexema = leitor.proximoCarater().toString();
        if(lexema.equals("-")){
            Character carater = leitor.proximoCarater();
            if(carater != null && carater == ' '){
                return TokenFactory.criar(lexema, leitor.posicao());
            }
            leitor.rollBack();
            return TokenFactory.criarInteiro(lexema+lerDigito().palavra(), leitor.posicao());
        }
        return TokenFactory.criar(lexema, leitor.posicao());
    }

    private void lerCaracteresDeAberturaOuFechamento() throws ErroLexico {
        String lexema = leitor.proximoCarater().toString();
        if (leitor.proximoCarater() == '*') {
            lerComentario();
            return;
        }
        bufferTokens.push(TokenFactory.criar(lexema, leitor.posicao()));
        leitor.rollBack();
    }

    private void lerComentario() throws ErroLexico {
        boolean isFimDoComentario = false;
        while (leitor.hasNext()) {
            Character carater = leitor.proximoCarater();
            if (carater == '*') {
                if (leitor.proximoCarater() == ')') {
                    isFimDoComentario = true;
                    break;
                }
                leitor.rollBack();
            }
        }
        if (!isFimDoComentario) {
            throw new ErroLexico(leitor.posicao(), "É necessário encerrar o comentário.");
        }
    }
}
