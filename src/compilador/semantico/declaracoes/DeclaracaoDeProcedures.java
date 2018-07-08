package compilador.semantico.declaracoes;

import compilador.Identificador.procedure.Parametro;
import compilador.Identificador.procedure.Procedure;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.AnalisadorDeEscopo;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.Stack;

public class DeclaracaoDeProcedures extends DeclaracaoDeIdentificadores {
    public DeclaracaoDeProcedures(Stack<Token> tokens) {
        super(tokens);
    }

    @Override
    protected void ler(Token token, Escopo escopo) throws ErroSemantico {
        Procedure procedure = new Procedure(token);
        while (token.codigo() != 47) {
            if (token.isIdentificador()) {
                procedure.adicionarParametro(new Parametro(token));
            }
            token = tokens.pop();
        }
        new AnalisadorDeEscopo(escopo, tokens).analisar();
    }
}
