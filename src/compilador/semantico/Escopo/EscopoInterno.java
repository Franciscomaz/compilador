package compilador.semantico.Escopo;

import compilador.Identificador.Identificador;
import compilador.Identificador.procedure.Parametro;
import compilador.Identificador.procedure.Parametros;
import compilador.semantico.ErroSemantico;
import compilador.token.Token;

public class EscopoInterno extends Escopo {
    private Escopo escopoPai;
    private Parametros parametros;

    public EscopoInterno(Escopo escopoPai) {
        this.escopoPai = escopoPai;
        this.parametros = new Parametros();
    }

    public void adicionarParametro(Parametro parametro) throws ErroSemantico {
        variaveis.adicionar(parametro);
        parametros.adicionar(parametro);
    }

    public Parametros getParametros(){
        return parametros;
    }

    public Identificador buscar(Token token) throws ErroSemantico {
        if(variaveis.contem(token.palavra())){
            return variaveis.buscar(token);
        }
        return escopoPai.buscar(token);
    }
}
