package compilador.semantico;

import compilador.Identificador.Identificador;
import compilador.Identificador.Procedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabelaDeIdentificadores<T extends Identificador> {
    private HashMap<String, T> identificadores;

    public TabelaDeIdentificadores() {
        this.identificadores = new HashMap<>();
    }

    public void adicionar(T identificador) throws ErroSemantico {
        if (contem(identificador)) {
            throw new ErroSemantico("identificador já existente " + identificador.nome());
        }
        identificadores.put(identificador.nome(), identificador);
    }

    public T buscar(String nome) {
        return identificadores.get(nome);
    }

    public void removerPeloNivel(int nivel) {
        List<String> chaves = new ArrayList<>();
        identificadores.forEach((k, v) -> {
            if (v.getClass() != Procedure.class) {
                if (v.nivel() >= nivel) chaves.add(k);
            }
        });
        chaves.forEach(chave -> identificadores.remove(chave));
    }

    public boolean contem(T identificador) {
        return identificadores.containsKey(identificador.nome());
    }
}
