package compilador.Identificador;

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

    public void adicionarTipo(String tipo) {
        parametros.adicionarTipo(tipo);
    }

    public boolean contemParametro(Parametro parametro, int posicao) throws ErroSemantico {
        try{
            Parametro param = parametros.get(posicao);
            return (param.getTipo().equals(parametro.getTipo()));
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
