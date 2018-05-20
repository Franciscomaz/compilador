package compilador.scanner;

import compilador.scanner.Leitor.Posicao;

public class ErroLexico extends Exception {
    ErroLexico(Posicao posicao, String descricao){
        super("Erro léxico na " 
                + posicao.toString()
                + '\n'
                + descricao
        );
    }
}
