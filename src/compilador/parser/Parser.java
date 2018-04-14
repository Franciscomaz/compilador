package compilador.parser;

import java.util.Stack;
import compilador.token.Token;
import compilador.simbolo.Simbolo;

public class Parser {

    private final Derivacao derivacao;
    private final Stack<Token> tokens;

    public Parser(Stack<Token> tokens) {
        this.tokens = tokens;
        this.derivacao = new Derivacao("PROGRAMA");
    }

    public void analisar() throws ErroSintatico {
        while (!tokens.empty()) {
            final Token token = tokens.peek();
            final Simbolo simbolo = derivacao.proximoSimbolo();
            if (simbolo.isTerminal()) {
                if (simbolo.getCodigo() == token.getCodigo()) {
                    tokens.pop();
                    derivacao.removerProducao();
                } else {
                    throw new ErroSintatico(token);
                }
            } else {
                derivacao.derivar(token);
            }
        }
        if (!tokens.empty() || !derivacao.isEmpty()) {
            throw new ErroSintatico("Erro sintatico");
        }
        System.out.println("Compilado com sucesso.");
    }
}
