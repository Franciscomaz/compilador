package compilador.semantico.Escopo;

import compilador.Identificador.Identificador;
import compilador.Identificador.procedure.Parametro;
import compilador.Identificador.procedure.Parametros;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Variaveis;

public class EscopoInterno implements Escopo {
    private Escopo escopoPai;
    private Variaveis variaveis;
    private Parametros parametros;

    public EscopoInterno(Escopo escopoPai) {
        this.variaveis = new Variaveis();
        this.escopoPai = escopoPai;
    }

    public void adicionarParametro(Parametro parametro) throws ErroSemantico {
        variaveis.adicionar(parametro);
        parametros.adicionar(parametro);
    }

    public void adicionarIdentificador(Identificador identificador) throws ErroSemantico {
        variaveis.adicionar(identificador);
    }

    public Identificador buscar(Identificador identificador) throws ErroSemantico {
        if(variaveis.contem(identificador)){
            return variaveis.buscar(identificador);
        }
        return escopoPai.buscar(identificador);
    }
}
