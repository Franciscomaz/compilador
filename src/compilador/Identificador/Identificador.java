package compilador.Identificador;

import compilador.simbolo.Simbolo;
import compilador.token.Token;

import java.util.Objects;

public abstract class Identificador extends Simbolo {

    private final int nivel;

    public Identificador(Token token, int nivel) {
        super(token.codigo(), token.palavra());
        this.nivel = nivel;
    }

    public int nivel() {
        return nivel;
    }

    public abstract String categoria();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identificador that = (Identificador) o;
        return this.nivel >= that.nivel &&
                Objects.equals(this.nome(), that.nome()) &&
                Objects.equals(this .categoria(), that.categoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nivel, nome());
    }

    @Override
    public String toString() {
        return "Nome:" + nome() + '\n' +
                "Nivel=" + nivel + '\n' +
                "Categoria=" + getClass();
    }
}
