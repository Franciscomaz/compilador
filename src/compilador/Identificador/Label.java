package compilador.Identificador;

import compilador.token.Token;

public class Label extends Identificador {

    public Label(Token token, int nivel) {
        super(token, nivel);
    }

    @Override
    public String categoria() {
        return "rotulo";
    }
}
