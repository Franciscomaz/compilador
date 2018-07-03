package compilador.semantico;

import compilador.Identificador.Identificador;
import compilador.Identificador.Procedure;

import java.util.*;

public class TabelaDeIdentificadores<T extends Identificador> {
    private HashMap<String, T> identificadores;
    private HashMap<Integer, Set<String>> verificador = new HashMap<>();

    public TabelaDeIdentificadores() {
        this.identificadores = new HashMap<>();
    }

    public void adicionar(int nivel, T identificador) throws ErroSemantico {
        adicionarNoVerificador(nivel, identificador);
        identificadores.put(identificador.nome(), identificador);
    }

    public T buscar(int nivel, String nome) throws ErroSemantico {
        verificar(nome, nivel);
        return identificadores.get(nome);
    }

    public void removerPeloNivel(int nivel) {
        List<String> chaves = new ArrayList<>();
        identificadores.forEach((k, v) -> {
            if (v.getClass() != Procedure.class) {
                if (v.nivel() >= nivel) {
                    chaves.add(k);
                    verificador.get(nivel).remove(v.nome());
                }
            }
        });
        chaves.forEach(chave -> identificadores.remove(chave));
    }

    private void verificar(String identificador, int nivel) throws ErroSemantico {
        if(verificador.get(nivel) == null || !verificador.get(nivel).contains(identificador)){
            throw new ErroSemantico("variavel não declarada " + identificador);
        }
    }

    private void adicionarNoVerificador(int nivel, Identificador identificador) throws ErroSemantico {
        Set<String> set = verificador.get(nivel);
        if(set == null){
            set = new HashSet<>();
        }
        set.add(identificador.nome());
        verificador.put(nivel, set);
    }

    public boolean contem(T identificador) {
        return identificadores.containsKey(identificador.nome());
    }
}
