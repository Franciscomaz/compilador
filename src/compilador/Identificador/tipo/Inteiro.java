package compilador.Identificador.tipo;

import java.util.Objects;

public class Inteiro implements Tipo{
    @Override
    public String nome() {
        return "inteiro";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inteiro inteiro = (Inteiro) o;
        return Objects.equals(nome(), inteiro.nome());
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome());
    }
}
