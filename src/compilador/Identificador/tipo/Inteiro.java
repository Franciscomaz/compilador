package compilador.Identificador.tipo;

public class Inteiro implements Tipo{
    @Override
    public String nome() {
        return "inteiro";
    }

    @Override
    public String toString() {
        return "Nome: " + nome();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass());
    }
}
