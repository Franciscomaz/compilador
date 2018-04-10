package compilador.token;

import compilador.scanner.Leitor.Posicao;

public class Token {
    private final int codigo;
    private final String palavra;
    private final Posicao posicao;
    
    public Token(int codigo, String lexema, Posicao posicao) {
        this.codigo = codigo;
        this.palavra = lexema;
        this.posicao = posicao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getPalavra() {
        return palavra;
    }

    public int getLinha(){
        return posicao.getLinha();
    }
    
    public int getColuna(){
        return posicao.getColuna();
    }
    
    @Override
    public String toString() {
        return codigo + "|" + palavra;
    }
}
