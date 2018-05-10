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
    
    public boolean isCategoria(){
        return codigo >= 1 || codigo <= 5;
    }
    
    public boolean isIdentificador(){
        return codigo == 25;
    }
    
    public boolean isTipo(){
        return codigo == 8 || codigo == 9;
    }
}
