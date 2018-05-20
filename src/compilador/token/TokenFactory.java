package compilador.token;

import compilador.scanner.Leitor;
import compilador.simbolo.TabelaDeSimbolos;

public class TokenFactory {
    
    public static Token criar(String simbolo, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().terminal(simbolo), posicao);
    }
    
    public static Token criarInteiro(String valor, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().terminalComValor("Inteiro", valor), posicao);
    }

    public static Token criarLiteral(String valor, Leitor.Posicao posicao) {
        return new Token(new TabelaDeSimbolos().terminalComValor("Literal", valor), posicao);
    }

    public static Token criarIdentificador(String valor, Leitor.Posicao posicao) {
        var tabelaDeSimbolos = new TabelaDeSimbolos();
        if (tabelaDeSimbolos.contemTerminal(valor) || valor.toUpperCase().matches("IDENTIFICADOR|INTEIRO|LITERAL")) {
            return new Token(tabelaDeSimbolos.terminal(valor), posicao);
        } else {
            return new Token(tabelaDeSimbolos.terminalComValor("Identificador", valor), posicao);
        }
    }
}
