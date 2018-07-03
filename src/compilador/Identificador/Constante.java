package compilador.Identificador;

import compilador.Identificador.tipo.Inteiro;
import compilador.Identificador.tipo.Tipo;
import compilador.token.Token;

public class Constante extends Variavel {

    public Constante(Token token, int nivel) {
        super(token, nivel);
    }

    @Override
    public String categoria() {
        return "constante";
    }

    public Tipo getTipo() {
        return new Inteiro();
    }
}
