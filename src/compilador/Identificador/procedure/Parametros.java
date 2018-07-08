package compilador.Identificador.procedure;

import compilador.semantico.ErroSemantico;

import java.util.ArrayList;
import java.util.List;

public class Parametros {
    private List<Parametro> parametros;

    public Parametros() {
        this.parametros = new ArrayList<>();
    }

    public void adicionar(Parametro parametro) {
        parametros.add(parametro);
    }

    public Parametro buscar(int posicao) throws ErroSemantico {
        if (isOutOfBounds(posicao)) {
            throw new ErroSemantico("numero de paramêtros " + posicao + ", maior que o esperado " + parametros.size());
        }
        return parametros.get(posicao);
    }

    private boolean isOutOfBounds(int posicao){
        return parametros.size() - 1 < posicao;
    }
}
