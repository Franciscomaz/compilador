package compilador.Identificador;

import compilador.Identificador.tipo.Tipo;
import compilador.semantico.ErroSemantico;
import compilador.semantico.ListaDeIdentificadores;
import compilador.token.Token;

public class Procedure extends Identificador {
    private ListaDeIdentificadores<Parametro> parametros;

    public Procedure(Token token, int nivel) {
        super(token, nivel);
        parametros = new ListaDeIdentificadores<>();
    }

    public void adicionarParametro(Parametro parametro) {
        parametros.add(parametro);
    }

    public void adicionarTipo(Tipo tipo) {
        parametros.adicionarTipo(tipo);
    }

    public Parametro getParametro(int posicao) throws ErroSemantico {
        try{
            return parametros.get(posicao);
        } catch (IndexOutOfBoundsException exception){
            throw new ErroSemantico("quantidade incorreta de parametros");
        }
    }

    public int quantidadeDeParametros() {
        return parametros.size();
    }

    @Override
    public String categoria() {
        return "procedure";
    }
}
