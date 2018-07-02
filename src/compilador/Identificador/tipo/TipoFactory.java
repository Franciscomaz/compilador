package compilador.Identificador.tipo;

import compilador.semantico.ErroSemantico;

public class TipoFactory {

    public static Tipo criar(String tipo) throws ErroSemantico {
        if(tipo.equals("INTEGER")){
            return new Inteiro();
        } else if(tipo.equals("ARRAY")) {
            return new Array();
        } else {
            throw new ErroSemantico("tipo desconhecido");
        }
    }
}
