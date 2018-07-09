package compilador.semantico.declaracoes;

import compilador.Identificador.Constante;
import compilador.Identificador.tipo.Inteiro;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.Stack;

public class DeclaracaoDeConstante extends DeclaracaoDeIdentificadores {
    Constante constante;

    public DeclaracaoDeConstante(Stack<Token> tokens) {
        super(tokens);
    }

    @Override
    protected void ler(Token token, Escopo escopo) throws ErroSemantico {
        if(token.isIdentificador()){
            constante = new Constante(token);
            escopo.adicionarIdentificador(constante);
        } else if(token.codigo() == 26) {
            constante.setTipo(new Inteiro());
            constante.setValor(token.palavra());
        }
    }
}
