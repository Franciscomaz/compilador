package compilador.lexico;

import compilador.lexico.Leitor.Posicao;

public class ErroLexico extends Exception {
    ErroLexico(Posicao posicao, String descricao){
        super("Erro léxico na " 
                + posicao.toString()
                + '\n'
                + descricao
        );
    }
}
