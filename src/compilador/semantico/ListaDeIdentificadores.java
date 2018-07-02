package compilador.semantico;

import compilador.Identificador.Identificador;
import compilador.Identificador.Variavel;
import compilador.Identificador.tipo.Tipo;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListaDeIdentificadores<T extends Identificador> extends ArrayList<T> {
    public ListaDeIdentificadores() {
    }

    public void adicionarTipo(Tipo tipo) {
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
        var temp = this
                .stream()
                .filter(ident -> identificador.nome().equals(ident.nome()))
                .findFirst()
                .orElse(null);
        if (temp == null) {
            throw new ErroSemantico(temp.nome() + " não declarado");
        }
        return temp;
    }

    boolean contem(Identificador identificador) {
        return this
                .stream()
                .anyMatch(ident -> identificador.nome().equals(ident.nome()));
    }
}
