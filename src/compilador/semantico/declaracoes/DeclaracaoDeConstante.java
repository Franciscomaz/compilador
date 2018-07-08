package compilador.semantico.declaracoes;

import compilador.Identificador.Constante;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.Stack;

public class DeclaracaoDeConstante extends DeclaracaoDeIdentificadores {
    public DeclaracaoDeConstante(Stack<Token> tokens) {
        super(tokens);
    }

    @Override
    protected void ler(Token token, Escopo escopo) throws ErroSemantico {
        escopo.adicionarIdentificador(new Constante(token));
    }
}
