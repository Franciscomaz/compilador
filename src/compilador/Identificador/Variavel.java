package compilador.Identificador;

import compilador.Identificador.tipo.Tipo;
import compilador.token.Token;

public class Variavel extends Identificador{
    private Tipo tipo;
    private String valor;

    public Variavel(Token token) {
        super(token);
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Variavel setTipo(Tipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
