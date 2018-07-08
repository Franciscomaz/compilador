package compilador.Identificador.procedure;

import compilador.Identificador.Identificador;
import compilador.semantico.ErroSemantico;
import compilador.token.Token;

public class Procedure extends Identificador {
    private Parametros parametros;

    public Procedure(Token token) {
        super(token);
        parametros = new Parametros();
    }

    public void adicionarParametro(Parametro parametro) {
        parametros.adicionar(parametro);
    }

    public Parametro getParametro(int posicao) throws ErroSemantico {
        return parametros.buscar(posicao);
    }
}
