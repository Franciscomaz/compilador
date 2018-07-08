package compilador.semantico.Escopo;

import compilador.Identificador.Identificador;
import compilador.Identificador.procedure.Procedure;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Variaveis;
import compilador.token.Token;

import java.util.HashMap;

public abstract class Escopo {
    protected Variaveis variaveis;
    protected HashMap<Procedure, EscopoInterno> procedures;

    public Escopo() {
        this.variaveis = new Variaveis();
        this.procedures = new HashMap<>();
    }

    public void adicionarProcedure(Procedure procedure) throws ErroSemantico {
        if (procedures.containsKey(procedure)){
            throw new ErroSemantico("procedure já declarada " + procedure.getPosicao());
        }
        procedures.put(procedure, new EscopoInterno(this));
    }

    public EscopoInterno getProcedure(Procedure procedure){
        return procedures.get(procedure);
    }

    public void adicionarIdentificador(Identificador identificador) throws ErroSemantico {
        variaveis.adicionar(identificador);
    }

    public abstract Identificador buscar(Token token) throws ErroSemantico;
}
