package compilador.token;

import compilador.scanner.Leitor.Posicao;
import compilador.simbolo.Simbolo;

public class Token {
    private final Simbolo simbolo;
    private final Posicao posicao;
    
    public Token(Simbolo simbolo, Posicao posicao) {
        this.simbolo = simbolo;
        this.posicao = posicao;
    }

    public int getCodigo() {
        return simbolo.getCodigo();
    }

    public String getPalavra() {
        return simbolo.getNome();
    }

    public int getLinha(){
        return posicao.getLinha();
    }
    
    public int getColuna(){
        return posicao.getColuna();
    }
    
    @Override
    public String toString() {
        return getCodigo() + "|" + getPalavra();
    }
}
