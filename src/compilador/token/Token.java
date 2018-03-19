package compilador.token;

public class Token {
    private final int codigo;
    private final String lexema;
    
    public Token(int codigo, String lexema) {
        this.codigo = codigo;
        this.lexema = lexema;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getLexema() {
        return lexema;
    }

    @Override
    public String toString() {
        return codigo + "|" + lexema;
    }
}
