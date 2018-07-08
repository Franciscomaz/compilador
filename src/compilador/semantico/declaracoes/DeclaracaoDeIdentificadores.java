package compilador.semantico.declaracoes;

import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;
import java.util.Stack;

public abstract class DeclaracaoDeIdentificadores {
    protected Stack<Token> tokens;

    public DeclaracaoDeIdentificadores(Stack<Token> tokens) {
        this.tokens = tokens;
    }

    public void execute(Escopo escopo) throws ErroSemantico {
        Token token = tokens.pop();
        while (deveParar(token)) {
            if (token.isCategoria()) {
                DeclaracaoDeIdentificadoresFactory.getReader(tokens).execute(escopo);
            } else if (token.isIdentificador()){
                ler(token, escopo);
            }
            token = tokens.pop();
        }
    }

    protected abstract void ler(Token token, Escopo escopo) throws ErroSemantico;

    private boolean deveParar(Token token) {
        return token.codigo() == 6;
    }
}
