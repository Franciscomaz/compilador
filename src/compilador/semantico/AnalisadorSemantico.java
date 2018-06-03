package compilador.semantico;

import compilador.Identificador.*;
import compilador.token.Token;

import java.util.Stack;

public class AnalisadorSemantico {
    private int nivel;
    private final Stack<Token> tokens;
    private ListaDeIdentificadores<Variavel> variaveisSemTipo;
    private ListaDeIdentificadores<Identificador> identificadores;

    public AnalisadorSemantico(Stack<Token> tokens) {
        this.nivel = -1;
        this.tokens = tokens;
        this.identificadores = new ListaDeIdentificadores<>();
        this.variaveisSemTipo = new ListaDeIdentificadores<>();
    }

    public void lerTokens() throws ErroSemantico {
        while (!tokens.empty()) {
            Token token = tokens.pop();
            lerDeclaracao(token);
            lerAtribuicao(token);
            verificarNivel(token);
        }
        identificadores.forEach(System.out::println);
    }

    private void lerDeclaracao(Token token) throws ErroSemantico {
        switch (token.palavra()) {
            case "PROGRAM":
                lerProgram();
                break;
            case "PROCEDURE":
                lerProcedure();
                break;
            case "VAR":
                lerVar();
                break;
            case "CONST":
                lerConstante();
                break;
            case "LABEL":
                lerRotulo();
                break;
        }
    }

    private void lerAtribuicao(Token token) throws ErroSemantico {
        if (token.isIdentificador()) {
            var variavel = new Variavel(token, nivel);
            if (!identificadores.contem(variavel)) {
                throw new ErroSemantico("variavel não declarada", token);
            }
        }
        switch (token.palavra()) {
            case "CALL":
                lerChamada();
                break;
        }
    }

    private void lerProgram() {
        identificadores.add(new Procedure(tokens.pop(), nivel));
    }

    private void lerVar() throws ErroSemantico {
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                var variavel = new Variavel(token, nivel);
                if (identificadores.contem(variavel) || variaveisSemTipo.contem(variavel)) {
                    throw new ErroSemantico("simbolo já declarado", token);
                }
                variaveisSemTipo.add(variavel);
            } else if (token.isTipo()) {
                variaveisSemTipo.adicionarTipo(token.palavra());
                identificadores.addAll(variaveisSemTipo);
                variaveisSemTipo.clear();
            } else if (!isTokenDeSeparacao(token)) {
                break;
            }
            token = tokens.pop();
        }
    }

    private void lerConstante() {
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                identificadores.add(new Constante(token, nivel));
            } else if (!isTokenDeSeparacao(token)) {
                break;
            }
            token = tokens.pop();
        }
    }

    private void lerProcedure() {
        Token token = tokens.pop();
        Procedure procedure = new Procedure(token, nivel);
        while (!token.palavra().equals(";")) {
            if (token.isIdentificador()) {
                procedure.adicionarParametro(new Parametro(token, nivel));
            } else if (token.isTipo()) {
                procedure.adicionarTipo(token.palavra());
            }
            token = tokens.pop();
        }
        identificadores.add(procedure);
    }

    private void lerRotulo() {
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                identificadores.add(new Label(token, nivel));
            } else if (!isTokenDeSeparacao(token)) {
                break;
            }
            token = tokens.pop();
        }
    }

    private void lerChamada() throws ErroSemantico {
        int posicao = 0;
        Token token = tokens.pop();
        var procedure = (Procedure) identificadores.buscar(new Procedure(token, nivel));
        while (!tokens.empty()) {
            token = tokens.pop();
            if (token.isIdentificador()) {
                var parametro = new Parametro(token, nivel);
                if (!procedure.contemParametro(parametro, posicao++)) {
                    throw new ErroSemantico("parametro não possui o mesmo tipo (" + parametro.getTipo() + ")");
                }
            }
        }
    }

    private void verificarNivel(Token token) {
        final String palavra = token.palavra();
        if (palavra.equals("PROGRAM") || palavra.equals("PROCEDURE") || palavra.equals("IF") || palavra.equals("WHILE") || palavra.equals("FOR")) {
            nivel++;
        } else if (palavra.equals("END")) {
            identificadores.deletarIdentificadoreDoNivel(nivel);
            nivel--;
        }
    }

    private boolean isTokenDeSeparacao(Token token) {
        return token.palavra().equals(";") || token.palavra().equals(",") || token.palavra().equals(":");
    }
}
