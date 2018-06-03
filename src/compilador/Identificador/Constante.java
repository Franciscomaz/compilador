package compilador.Identificador;

import compilador.token.Token;

public class Constante extends Identificador {

    public Constante(Token token, int nivel) {
        super(token, nivel);
    }

    @Override
    public String categoria() {
        return "constante";
    }
}
