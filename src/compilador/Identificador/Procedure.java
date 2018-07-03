package compilador.Identificador;

import compilador.Identificador.tipo.Tipo;
import compilador.semantico.ErroSemantico;
import compilador.semantico.TabelaDeIdentificadores;
import compilador.token.Token;

import java.util.ArrayList;
import java.util.List;

public class Procedure extends Identificador {
    private List<Parametro> parametros;

    public Procedure(Token token, int nivel) {
        super(token, nivel);
        parametros = new ArrayList<>();
    }

    public void adicionarParametro(Parametro parametro) {
        parametros.add(parametro);
    }

    public Parametro getParametro(int posicao) throws ErroSemantico {
        try{
            return parametros.get(posicao);
        } catch (IndexOutOfBoundsException exception){
            throw new ErroSemantico("quantidade incorreta de parametros", getPosicao());
        }
    }

    @Override
    public String categoria() {
        return "procedure";
    }
}
