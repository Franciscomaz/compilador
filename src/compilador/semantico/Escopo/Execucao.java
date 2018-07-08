package compilador.semantico.Escopo;

import compilador.semantico.ErroSemantico;
import compilador.token.Token;

import java.util.Stack;

public class Execucao {
    private int nivel;
    private Escopo escopo;
    private Stack<Token> tokens;

    public Execucao(Escopo escopo, Stack<Token> tokens) {
        this.nivel = 1;
        this.escopo = escopo;
        this.tokens = tokens;
    }

    public void analisar() throws ErroSemantico {
        Token token = tokens.pop();
        while (deveParar(token)) {
            if (token.isIdentificador()) {
                escopo.buscar(token);
            } else if (token.codigo() == 6) {
                nivel++;
            } else if (token.codigo() == 7) {
                nivel--;
            }
        }
    }

    public boolean deveParar(Token token) {
        return token.codigo() == 7 && nivel == 1;
    }
}
