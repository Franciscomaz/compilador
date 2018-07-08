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
}
