package compilador.token;

import compilador.scanner.Leitor.Posicao;

public class Token {
    private final int codigo;
    private final String lexema;
    private final Posicao posicao;
    
    public Token(int codigo, String lexema, Posicao posicao) {
        this.codigo = codigo;
        this.lexema = lexema;
        this.posicao = posicao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getLexema() {
        return lexema;
    }

    public int getLinha(){
        return posicao.getLinha();
    }
    
    public int getColuna(){
        return posicao.getColuna();
    }
    
    @Override
    public String toString() {
        return codigo + "|" + lexema;
    }
}
