package compilador.semantico;

import compilador.simbolo.Identificador;
import compilador.token.Token;

import java.util.Stack;

public class AnalisadorSemantico {
    private String categoria;
    private Integer nivel = -1;
    private Stack<Identificador> identificadores;
    private Stack<Identificador> temp;

    public AnalisadorSemantico() {
        this.temp = new Stack<>();
        this.identificadores = new Stack<>();
    }

    public void lerToken(Token token) throws ErroSemantico {
        System.out.println("Token: " + token);
        if (token.isIdentificador()) {
            System.out.println("É identificador.");
            if ("VAR".equals(categoria)) {
                Identificador identificador = new Identificador(token, nivel, categoria);
                if (temIdentificador(identificador)) {
                    System.out.println("Identificadores: " + identificadores.contains(identificador));
                    System.out.println("Temp: " + temp.contains(identificador));
                    throw new ErroSemantico(token);
                }
                temp.push(identificador);
            }
        } else if (token.isCategoria()) {
            System.out.println("É categoria.");
            categoria = token.palavra();
        } else if (token.isTipo()) {
            System.out.println("É tipo.");
            while (!temp.empty()) {
                Identificador identificador = temp.pop();
                identificador.setTipo(token.palavra());
                identificadores.push(identificador);
            }
        }
        verificarNivel(token);
    }

    private void verificarNivel(Token token) {
        String palavra = token.palavra();
        if (palavra.equals("PROGRAM") ||palavra.equals("PROCEDURE") || palavra.equals("IF") || palavra.equals("WHILE") || palavra.equals("FOR")) {
            System.out.println("Incrementar nível.");
            nivel++;
        } else if (palavra.equals("END")) {
            System.out.println("Decrementar nível.");
            nivel--;
        }
    }

    public boolean temIdentificador(Identificador identificador) {
        return identificadores.contains(identificador) || temp.contains(identificador);
    }
}
