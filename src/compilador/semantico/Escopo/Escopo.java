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

    public void adicionarProcedure(Procedure procedure) {
        procedures.put(procedure, new EscopoInterno(this));
    }

    public void adicionarIdentificador(Identificador identificador) throws ErroSemantico {
        variaveis.adicionar(identificador);
    }

    public abstract Identificador buscar(Token token) throws ErroSemantico;
}
