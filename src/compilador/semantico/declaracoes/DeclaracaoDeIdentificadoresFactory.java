package compilador.semantico.declaracoes;

import compilador.semantico.ErroSemantico;
import compilador.token.Token;

import java.util.Stack;

public class DeclaracaoDeIdentificadoresFactory {
    public static DeclaracaoDeIdentificadores getReader(Stack<Token> tokens) throws ErroSemantico {
        Token token = tokens.pop();
        if (token.codigo() == 2) {
            return new DeclaracaoDeLabels(tokens);
        } else if (token.codigo() == 3) {
            return new DeclaracaoDeConstante(tokens);
        } else if (token.codigo() == 4) {
            return new DeclaracaoDeVariaveis(tokens);
        } else {
            throw new ErroSemantico("categoria não existente ", token);
        }
    }
}
