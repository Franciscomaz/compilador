package compilador.token;

import compilador.scanner.Leitor;
import compilador.simbolo.Simbolo;
import compilador.simbolo.TabelaDeSimbolos;

public class TokenFactory {
    
    public static Token criarToken(String palavra, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().getSimboloTerminal(palavra), posicao);
    }
    
    public static Token criarInteiro(String palavra, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().getSimboloComValor("Inteiro", palavra), posicao);
    }

    public static Token criarLiteral(String palavra, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().getSimboloComValor("Literal", palavra), posicao);
    }

    public static Token criarIdentificador(String palavra, Leitor.Posicao posicao) {
        TabelaDeSimbolos tabelaDeSimbolos = new TabelaDeSimbolos();
        if (tabelaDeSimbolos.contemTerminal(palavra)) {
            return new Token(tabelaDeSimbolos.getSimboloTerminal(palavra), posicao);
        } else {
            return new Token(tabelaDeSimbolos.getSimboloComValor("Identificador", palavra), posicao);
        }
    }
}
