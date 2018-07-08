package compilador.semantico.Escopo;

import compilador.semantico.ErroSemantico;
import compilador.token.Token;

import java.util.Stack;

public class AnalisadorDeEscopo {
    private final Stack<Token> tokens;
    private Escopo escopo;

    public AnalisadorDeEscopo(Escopo escopo, Stack<Token> tokens) {
        this.escopo = escopo;
        this.tokens = tokens;
    }

    public void analisar() throws ErroSemantico {
        new Declaracao(escopo, tokens).analisar();
        new Execucao().analisar();
    }
}
