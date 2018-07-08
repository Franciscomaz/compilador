package compilador.semantico.declaracoes;

import compilador.Identificador.Label;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.Stack;

public class DeclaracaoDeLabels extends DeclaracaoDeIdentificadores {
    public DeclaracaoDeLabels(Stack<Token> tokens) {
        super(tokens);
    }

    @Override
    protected void ler(Token token, Escopo escopo) throws ErroSemantico {
        escopo.adicionarIdentificador(new Label(token));
    }
}
