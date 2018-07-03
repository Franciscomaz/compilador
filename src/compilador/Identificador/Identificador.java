package compilador.Identificador;

import compilador.lexico.Leitor;
import compilador.simbolo.Simbolo;
import compilador.token.Token;

import java.util.Objects;

public abstract class Identificador extends Simbolo {

    private final int nivel;
    private final Leitor.Posicao posicao;

    public Identificador(Token token, int nivel) {
        super(token.codigo(), token.palavra());
        this.posicao = token.posicao();
        this.nivel = nivel;
    }

    public int nivel() {
        return nivel;
    }

    public Leitor.Posicao getPosicao(){
        return posicao;
    }

    public abstract String categoria();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identificador that = (Identificador) o;
        return Objects.equals(this.nome(), that.nome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nivel, nome());
    }

    @Override
    public String toString() {
        return "Nome:" + nome() + '\n' +
                "Nivel=" + nivel + '\n' +
                "Categoria=" + getClass();
    }
}
