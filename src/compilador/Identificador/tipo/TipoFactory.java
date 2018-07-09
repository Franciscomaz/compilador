package compilador.Identificador.tipo;

import compilador.semantico.ErroSemantico;
import compilador.token.Token;

import java.util.Stack;

public class TipoFactory {
    public static Tipo criar(String tipo, Stack<Token> tokens) throws ErroSemantico {
        if(tipo.equals("INTEGER")){
            return new Inteiro();
        } else if(tipo.equals("ARRAY")) {
            return criarArray(tokens);
        } else {
            throw new ErroSemantico("tipo desconhecido");
        }
    }

    public static Array criarArray(Stack<Token> tokens) throws ErroSemantico {
        tokens.pop();
        int indiceInicial = Integer.parseInt(tokens.pop().palavra());
        tokens.pop();
        int indiceFinal = Integer.parseInt(tokens.pop().palavra());
        tokens.pop();
        tokens.pop();
        Tipo tipo = criar(tokens.pop().palavra(), tokens);
        return new Array(tipo, indiceInicial, indiceFinal);

    }
}
