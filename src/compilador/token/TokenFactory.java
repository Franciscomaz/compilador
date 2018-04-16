package compilador.token;

import compilador.scanner.Leitor;
import compilador.simbolo.TabelaDeSimbolos;

public class TokenFactory {
    
    public static Token criarToken(String simbolo, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().getSimboloTerminal(simbolo), posicao);
    }
    
    public static Token criarInteiro(String valor, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().getSimboloComValor("Inteiro", valor), posicao);
    }

    public static Token criarLiteral(String valor, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().getSimboloComValor("Literal", valor), posicao);
    }

    public static Token criarIdentificador(String valor, Leitor.Posicao posicao) {
        TabelaDeSimbolos tabelaDeSimbolos = new TabelaDeSimbolos();
        if (tabelaDeSimbolos.contemTerminal(valor)) {
            return new Token(tabelaDeSimbolos.getSimboloTerminal(valor), posicao);
        } else {
            return new Token(tabelaDeSimbolos.getSimboloComValor("Identificador", valor), posicao);
        }
    }
}
