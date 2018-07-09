package compilador.semantico;

import compilador.Identificador.*;
import compilador.semantico.Escopo.AnalisadorDeEscopo;
import compilador.semantico.Escopo.EscopoGlobal;
import compilador.token.Token;

import java.util.Stack;

public class AnalisadorSemantico {
    private EscopoGlobal escopo;
    private final Stack<Token> tokens;

    public AnalisadorSemantico(Stack<Token> tokens) {
        this.tokens = tokens;
        init();
    }

    private void init(){
        Token token = tokens.pop();
        while(token.codigo() != 47){
            if(token.isIdentificador()){
                escopo = new EscopoGlobal(new Program(token));
            }
            token = tokens.pop();
        }
    }

    public void lerTokens() throws ErroSemantico {
        new AnalisadorDeEscopo(escopo, tokens).analisar();
    }
}
