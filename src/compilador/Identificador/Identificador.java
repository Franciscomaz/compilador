package compilador.Identificador;

import compilador.simbolo.Simbolo;
import compilador.token.Token;

import java.util.Objects;

public abstract class Identificador extends Simbolo {

    public Identificador(Token token) {
        super(token.codigo(), token.palavra());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identificador that = (Identificador) o;
        return Objects.equals(this.nome(), that.nome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome());
    }

    @Override
    public String toString() {
        return "Nome:" + nome() + '\n' +
                "Categoria=" + getClass();
    }
}
