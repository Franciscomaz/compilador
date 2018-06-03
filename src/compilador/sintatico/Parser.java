package compilador.sintatico;

import java.util.Stack;

import compilador.semantico.AnalisadorSemantico;
import compilador.semantico.ErroSemantico;
import compilador.token.Token;
import compilador.simbolo.Simbolo;

public class Parser {

    private final Derivacao derivacao;
    private final Stack<Token> tokens;
    private final AnalisadorSemantico analisadorSemantico;

    public Parser(Stack<Token> tokens) {
        this.tokens = tokens;
        this.derivacao = new Derivacao("PROGRAMA");
        this.analisadorSemantico = new AnalisadorSemantico(tokens);
    }

    public void analisar() throws ErroSintatico, ErroSemantico {
        while (!tokens.empty()) {
            final Token token = tokens.peek();
            final Simbolo simbolo = derivacao.proximaProducao();
            if (simbolo.isTerminal()) {
                if (simbolo.codigo() == token.codigo()) {
                    tokens.pop();
                    derivacao.removerProducao();
                } else {
                    throw new ErroSintatico(token);
                }
            } else {
                derivacao.derivar(token);
            }
        }
        if (!tokens.empty() || !derivacao.isEmpty()) {
            throw new ErroSintatico("Erro sintático.");
        }
    }
}
