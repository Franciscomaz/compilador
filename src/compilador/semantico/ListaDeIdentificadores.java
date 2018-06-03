package compilador.semantico;

import compilador.Identificador.Identificador;
import compilador.Identificador.Variavel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListaDeIdentificadores<T extends Identificador> extends ArrayList<T> {
    public ListaDeIdentificadores() {
    }

    public void adicionarTipo(String tipo) {
        for (var identificador : this) {
            if (identificador instanceof Variavel) {
                ((Variavel) identificador).setTipo(tipo);
            }
        }
    }

    public void deletarIdentificadoreDoNivel(int nivel) {
        var identificadoresQueSeramRemovidos = this
                .stream()
                .filter(identificador -> identificador.nivel() == nivel
                        && !identificador.categoria().equals("PROCEDURE"))
                .collect(Collectors.toList());
        this.removeAll(identificadoresQueSeramRemovidos);
    }

    public Identificador buscar(Identificador identificador) throws ErroSemantico {
        identificador = this
                .stream()
                .filter(identificador::equals)
                .findFirst()
                .orElse(null);
        if (identificador == null) {
            throw new ErroSemantico(identificador.categoria() + " não declarado");
        }
        return identificador;
    }

    boolean contem(Identificador identificador) {
        return this
                .stream()
                .anyMatch(identificador::equals);
    }
}
