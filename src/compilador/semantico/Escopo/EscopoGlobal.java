package compilador.semantico.Escopo;

import compilador.Identificador.Identificador;
import compilador.Identificador.procedure.Procedure;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Variaveis;

import java.util.HashMap;

public class EscopoGlobal implements Escopo {
    private final Identificador program;
    private Variaveis variaveis;
    private HashMap<Procedure, EscopoInterno> procedures;

    public EscopoGlobal(Identificador program) {
        this.program = program;
        this.variaveis = new Variaveis();
        this.procedures = new HashMap<>();
    }

    public void adicionarProcedure(Procedure procedure){
        procedures.put(procedure, new EscopoInterno(this));
    }

    public void adicionarIdentificador(Identificador identificador) throws ErroSemantico {
        variaveis.adicionar(identificador);
    }

    public Identificador buscar(Identificador identificador) throws ErroSemantico {
        if(!variaveis.contem(identificador)){
            throw new ErroSemantico("identificador não declarado");
        }
        return variaveis.buscar(identificador);
    }
}
