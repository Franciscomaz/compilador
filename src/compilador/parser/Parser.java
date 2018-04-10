package compilador.parser;

import compilador.simbolo.Simbolo;
import compilador.simbolo.TabelaDeSimbolos;
import compilador.token.TabelaDeTokens;
import compilador.token.Token;
import java.util.Stack;

public class Parser {

    private final Stack<Token> a;
    private final MatrizDeParsing matriz;
    private final Stack<Simbolo> x;

    public Parser(Stack<Token> tokens) {
        this.a = tokens;
        this.matriz = new MatrizDeParsing();
        this.x = new Stack<>();
        this.x.push(getSimbolo("PROGRAMA"));
    }

    public void analisar() throws ErroSintatico {
        while (!a.empty()) {
            Token token = a.peek();
            Simbolo simbolo = x.peek();
            if (simbolo.isTerminal()) {
                if (simbolo.getCodigo() == token.getCodigo()) {
                    a.pop();
                    x.pop();
                } else {
                    throw new ErroSintatico(token);
                }
            } else {
                if (matriz.getDerivacao(simbolo, token) != null) {
                    if (matriz.getDerivacao(simbolo, token).equals("NULL")) {
                        x.pop();
                    } else {
                        adicionarNaPilha(matriz.getDerivacao(x.pop(), token));
                    }
                } else {
                    throw new ErroSintatico(token);
                }
            }
        }
        if (a.empty() && x.empty()) {
            System.out.println("Compilado com sucesso.");
        } else {
            throw new ErroSintatico("Erro sintatico");
        }
    }

    private void adicionarNaPilha(String derivacao) {
        String[] producao = derivacao.split("\\|");
        for (int i = producao.length - 1; 0 <= i; i--) {
            x.push(getSimbolo(producao[i]));
        }
    }

    private Simbolo getSimbolo(String palavra) {
        TabelaDeTokens tabelaDeTokens = new TabelaDeTokens();
        TabelaDeSimbolos tabelaDeSimbolos = new TabelaDeSimbolos();
        if (tabelaDeSimbolos.contemSimbolo(palavra)) {
            return new Simbolo(tabelaDeSimbolos.getCodigo(palavra), palavra);
        } else {
            return new Simbolo(tabelaDeTokens.getCodigo(palavra), palavra);
        }
    }
}
