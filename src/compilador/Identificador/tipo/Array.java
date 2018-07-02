package compilador.Identificador.tipo;

import compilador.semantico.ErroSemantico;

public class Array implements Tipo{
    private Tipo tipo = null;
    private int indexInicial = 0;
    private int indexFinal = 0;

    @Override
    public String nome() {
        return "array";
    }

    public void setInicio(int index){
        indexInicial = index;
    }

    public void setFinal(int index) throws ErroSemantico {
        if(index < indexInicial){
            throw new ErroSemantico("array inválido");
        }
        indexFinal = index;
    }

    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }
}
