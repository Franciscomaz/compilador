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

    public int codigo() {
        return simbolo.codigo();
    }

    public String palavra() {
        return simbolo.nome();
    }

    public Posicao posicao(){
        return posicao;
    }

    public boolean isCategoria() {
        return codigo() >= 1 && codigo() <= 5;
    }

    public boolean isIdentificador() {
        return codigo() == 25;
    }

    public boolean isTipo() {
        return codigo() == 8 || codigo() == 9;
    }

    @Override
    public String toString() {
        return codigo() + "|" + palavra();
    }
}
