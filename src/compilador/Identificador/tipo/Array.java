package compilador.Identificador.tipo;

import compilador.semantico.ErroSemantico;

public class Array implements Tipo{
    private Tipo tipo;
    private int indexInicial;
    private int indexFinal;

    public Array(Tipo tipo, int indexInicial, int indexFinal) throws ErroSemantico {
        this.tipo = tipo;
        this.indexInicial = indexInicial;
        setFinal(indexFinal);
    }

    @Override
    public String nome() {
        return "array";
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getIndexInicial() {
        return indexInicial;
    }

    public int getIndexFinal() {
        return indexFinal;
    }

    public void setFinal(int index) throws ErroSemantico {
        if(index < indexInicial){
            throw new ErroSemantico("array inválido");
        }
        indexFinal = index;
    }

    @Override
    public String toString() {
        return "Array{" +
                "tipo=" + tipo +
                ", indexInicial=" + indexInicial +
                ", indexFinal=" + indexFinal +
                '}';
    }
}
