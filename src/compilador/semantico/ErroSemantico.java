package compilador.semantico;

import compilador.lexico.Leitor;
import compilador.token.Token;

public class ErroSemantico extends Exception {
    public ErroSemantico(String descricao, Token token) {
        super("Erro semântico: " + descricao + "\n" + token + " " + token.posicao());
    }

    public ErroSemantico(String descricao, Leitor.Posicao posicao) {
        super("Erro semântico: " + descricao + "\n" + " " + posicao);
    }

    public ErroSemantico(String descricao) {
       super ("Erro semântico: " + descricao);
    }
}
