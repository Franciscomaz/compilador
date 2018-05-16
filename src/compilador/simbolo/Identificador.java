package compilador.simbolo;

import compilador.token.Token;

import java.util.Objects;

public class Identificador extends Simbolo {

    private final int nivel;
    private String tipo;
    private final String categoria;

    public Identificador(int codigo, String nome, int nivel, String categoria) {
        super(codigo, nome);
        this.nivel = nivel;
        this.categoria = categoria;
    }

    public Identificador(Token token, int nivel, String categoria) {
        super(token.codigo(), token.palavra());
        this.nivel = nivel;
        this.categoria = categoria;
    }

    public int nivel() {
        return nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String categoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identificador that = (Identificador) o;
        return this.nivel >= that.nivel &&
                Objects.equals(this.nome(), that.nome()) &&
                Objects.equals(this.categoria(), that.categoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nivel, nome());
    }

    @Override
    public String toString() {
        return "Identificador{" +
                "nivel=" + nivel +
                ", tipo='" + tipo + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
