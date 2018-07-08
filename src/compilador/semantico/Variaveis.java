package compilador.semantico;

import compilador.Identificador.Identificador;
import compilador.token.Token;

import java.util.HashMap;

public class Variaveis {
    private HashMap<String, Identificador> identificadores;

    public Variaveis() {
        identificadores = new HashMap<>();
    }

    public void adicionar(Identificador identificador) throws ErroSemantico {
        if(contem(identificador.nome())){
            throw new ErroSemantico("identificador já declarado" + identificador.nome());
        }
        identificadores.put(identificador.nome(), identificador);
    }

    public Identificador buscar(Token token){
        return identificadores.get(token.palavra());
    }

    public boolean contem(String nome){
        return identificadores.containsKey(nome);
    }
}
