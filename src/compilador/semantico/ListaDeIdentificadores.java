package compilador.semantico;

import compilador.simbolo.Identificador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ListaDeIdentificadores {
    private List<Identificador> identificadores;

    ListaDeIdentificadores() {
        this.identificadores = new ArrayList<>();
    }

    void adicionar(Identificador identificador) {
        identificadores.add(identificador);
    }

    void deletarIdentificadoreDoNivel(int nivel) {
        var identificadoresQueSeramRemovidos = identificadores
                .stream()
                .filter(identificador -> identificador.nivel() == nivel
                        && !identificador.categoria().equals("PROCEDURE"))
                .collect(Collectors.toList());
        identificadores.removeAll(identificadoresQueSeramRemovidos);
    }

    boolean contem(Identificador identificador) {
        return identificadores
                .stream()
                .anyMatch(identificador::equals);
    }
}
