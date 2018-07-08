package compilador.semantico.declaracoes;

import compilador.Identificador.Variavel;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.Stack;

public class DeclaracaoDeVariaveis extends DeclaracaoDeIdentificadores {
    public DeclaracaoDeVariaveis(Stack<Token> tokens) {
        super(tokens);
    }

    @Override
    protected void ler(Token token, Escopo escopo) throws ErroSemantico {
        escopo.adicionarIdentificador(new Variavel(token));
    }
}
