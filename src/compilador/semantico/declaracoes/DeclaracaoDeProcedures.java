package compilador.semantico.declaracoes;

import compilador.Identificador.procedure.Parametro;
import compilador.Identificador.procedure.Procedure;
import compilador.Identificador.tipo.Tipo;
import compilador.Identificador.tipo.TipoFactory;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.AnalisadorDeEscopo;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DeclaracaoDeProcedures extends DeclaracaoDeIdentificadores {
    Set<Parametro> parametros;

    public DeclaracaoDeProcedures(Stack<Token> tokens) {
        super(tokens);
        parametros = new HashSet<>();
    }

    @Override
    protected void ler(Token token, Escopo escopo) throws ErroSemantico {
        if(!token.isIdentificador()){
            return;
        }
        Procedure procedure = new Procedure(token);
        escopo.adicionarProcedure(procedure);
        while (token.codigo() != 47) {
            token = tokens.pop();
            if (token.isIdentificador()) {
                Parametro parametro = new Parametro(token);
                escopo.getProcedure(procedure).adicionarParametro(parametro);
                parametros.add(parametro);
            } else if (token.isTipo()){
                Tipo tipo = TipoFactory.criar(token.palavra(), tokens);
                parametros.forEach(parametro -> parametro.setTipo(tipo));
                parametros.clear();
            }
        }
        new AnalisadorDeEscopo(escopo.getProcedure(procedure), tokens).analisar();
    }
}
