package compilador.semantico.Escopo;

import compilador.Identificador.Identificador;
import compilador.semantico.ErroSemantico;

public interface Escopo {
    public Identificador buscar(Identificador identificador) throws ErroSemantico;
}
