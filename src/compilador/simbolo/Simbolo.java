package compilador.simbolo;

public class Simbolo {
    private final int codigo;
    private final String nome;

    Simbolo(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int codigo() {
        return codigo;
    }

    public String nome() {
        return nome;
    }

    public boolean isTerminal() {
        return codigo < 52;
    }
}
