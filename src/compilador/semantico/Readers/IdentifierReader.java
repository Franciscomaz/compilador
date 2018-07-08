package compilador.semantico.Readers;

import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.Stack;

public abstract class IdentifierReader {
    public void execute(Stack<Token> tokens, Escopo escopo){
        if(!deveParar(tokens.pop())){

        }
    }

    public abstract void read();

    private boolean deveParar(Token token) {
        return token.palavra().equals("BEGIN");
    }
}
