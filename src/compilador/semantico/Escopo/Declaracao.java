package compilador.semantico.Escopo;

import compilador.semantico.ErroSemantico;
import compilador.semantico.declaracoes.*;
import compilador.token.Token;

import java.util.Stack;

public class Declaracao {
    private Escopo escopo;
    private Stack<Token> tokens;

    public Declaracao(Escopo escopo, Stack<Token> tokens) {
        this.escopo = escopo;
        this.tokens = tokens;
    }

    public void analisar() throws ErroSemantico {
        Token token = tokens.pop();
        if(token.codigo() == 6){
            return;
        }
        DeclaracaoDeIdentificadoresFactory.getReader(tokens, token).execute(escopo);
    }
}
