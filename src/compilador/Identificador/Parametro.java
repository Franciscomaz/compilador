package compilador.Identificador;

import compilador.token.Token;

public class Parametro extends Variavel{
    public Parametro(Token token, int nivel) {
        super(token, nivel);
    }

    @Override
    public String categoria() {
        return "parametro";
    }
}
