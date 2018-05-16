package compilador.semantico;

import compilador.simbolo.Identificador;
import compilador.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalisadorSemantico {
    private String categoria;
    private Integer nivel = -1;
    private List<Identificador> identificadores;
    private List<Identificador> temp;

    public AnalisadorSemantico() {
        this.temp = new ArrayList<>();
        this.identificadores = new ArrayList<>();
    }

    public void lerToken(Token token) throws ErroSemantico {
        if (token.isIdentificador()) {
            String nomeCategoria = "";
            if ("VAR".equals(categoria) || "CONST".equals(categoria)) {
                nomeCategoria = "variavel";
            } else if ("PROCEDURE".equals(categoria)) {
                nomeCategoria = "procedure";
            } else if ("LABEL".equals(categoria)) {
                nomeCategoria = "rotulo";
            }
            Identificador identificador = new Identificador(token, nivel, nomeCategoria);
            if (temIdentificador(identificador)) {
                throw new ErroSemantico(token);
            }
            identificadores.add(identificador);
        } else if (token.isCategoria()) {
            categoria = token.palavra();
        }
        verificarNivel(token);
    }

    private void verificarNivel(Token token) {
        String palavra = token.palavra();
        if (palavra.equals("PROGRAM") || palavra.equals("PROCEDURE") || palavra.equals("IF") || palavra.equals("WHILE") || palavra.equals("FOR")) {
            System.out.println("Incrementar nível.");
            nivel++;
        } else if (palavra.equals("END")) {
            System.out.println("Decrementar nível.");
            deletarIdentificadores();
            nivel--;
        }
    }

    public void deletarIdentificadores() {
        List<Identificador> identificadoresQueSeramRemovidos = identificadores
                .stream()
                .filter(identificador -> identificador.nivel() == nivel
                && !identificador.categoria().equals("PROCEDURE"))
                .collect(Collectors.toList());
        identificadoresQueSeramRemovidos.forEach(System.out::println);
        identificadores.removeAll(identificadoresQueSeramRemovidos);
    }

    public boolean temIdentificador(Identificador identificador) {
        return identificadores
                .stream()
                .anyMatch(identificador::equals);
    }
}
