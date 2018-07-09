package compilador.semantico;

import compilador.Identificador.Identificador;
import compilador.token.Token;

public class ErroSemantico extends Exception {
    public ErroSemantico(String descricao, Token token) {
        super("Erro semântico: " + descricao + "\n" + token + " " + token.posicao());
    }

    public ErroSemantico(String descricao, Identificador identificador) {
        super("Erro semântico: " + descricao + "\n" + identificador + " " + identificador.getPosicao());
    }

    public ErroSemantico(String descricao) {
       super ("Erro semântico: " + descricao);
    }
}
