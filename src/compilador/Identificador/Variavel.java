package compilador.Identificador;

import compilador.token.Token;

public class Variavel extends Identificador{
    private String tipo;

    public Variavel(Token token, int nivel) {
        super(token, nivel);
    }

    public String getTipo() {
        return tipo;
    }

    public Variavel setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public String categoria() {
        return "variavel";
    }
}
