package compilador.semantico;

import compilador.token.Token;

public class ErroSemantico extends Exception {
    ErroSemantico(String descricao, Token token) {
        super("Erro semântico: " + descricao + "\n" + token + " " + token.posicao());
    }
}
