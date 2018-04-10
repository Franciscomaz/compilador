package compilador.simbolo;

public class Simbolo {
    private final int codigo;
    private final String nome;

    public Simbolo(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public boolean isTerminal(){
        return codigo < 52;
    }
}
