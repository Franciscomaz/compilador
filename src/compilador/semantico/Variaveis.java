package compilador.semantico;

import compilador.Identificador.Identificador;

import java.util.HashSet;
import java.util.Set;

public class Variaveis {
    private Set<Identificador> identificadores;

    public Variaveis() {
        identificadores = new HashSet<>();
    }

    public void adicionar(Identificador identificador) throws ErroSemantico {
        if(contem(identificador)){
            throw new ErroSemantico("identificador já declarado" + identificador.nome());
        }
        identificadores.add(identificador);
    }

    public Identificador buscar(Identificador identificador){
        return identificadores
                .stream()
                .filter(identificador::equals)
                .findFirst()
                .get();
    }

    public boolean contem(Identificador identificador){
        return identificadores.contains(identificador);
    }
}
