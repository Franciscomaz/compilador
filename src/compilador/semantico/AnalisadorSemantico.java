package compilador.semantico;

import compilador.Identificador.*;
import compilador.token.Token;

import java.util.Stack;

public class AnalisadorSemantico {
    private int nivel;
    private String categoria;
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
        if(isCategoria(token)){
            categoria = token.palavra();
        }
        if(token.palavra().equals("BEGIN")){
            categoria = "";
        }
        switch (categoria) {
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
        categoria = "";
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
            } else if(isCategoria(token) || token.palavra().equals("BEGIN")){
                lerDeclaracao(token);
                break;
            }
            token = tokens.pop();
        }
    }

    private void lerConstante() throws ErroSemantico {
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                identificadores.add(new Constante(token, nivel));
            } else if(isCategoria(token) || token.palavra().equals("BEGIN")){
                lerDeclaracao(token);
                break;
            }
            token = tokens.pop();
        }
    }

    private void lerProcedure() throws ErroSemantico {
        Token token = tokens.pop();
        Procedure procedure = new Procedure(token, nivel);
        if(identificadores.contem(procedure)){
            throw new ErroSemantico("procedure já declarada", token);
        }
        while (!token.palavra().equals(";")) {
            token = tokens.pop();
            if (token.isIdentificador()) {
                var parametro = new Parametro(token, nivel);
                identificadores.add(parametro);
                procedure.adicionarParametro(parametro);
            } else if (token.isTipo()) {
                procedure.adicionarTipo(token.palavra());
            }
        }
        categoria = "";
        identificadores.add(procedure);
    }

    private void lerRotulo() throws ErroSemantico {
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                identificadores.add(new Label(token, nivel));
            } else if(isCategoria(token) || token.palavra().equals("BEGIN")){
                lerDeclaracao(token);
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
        if (palavra.equals("PROGRAM") || palavra.equals("PROCEDURE") || palavra.equals("IF") || palavra.equals("WHILE") || palavra.equals("FOR") || palavra.equals("REPEAT")) {
            nivel++;
        } else if (palavra.equals("END")) {
            identificadores.deletarIdentificadoreDoNivel(nivel);
            nivel--;
        }
    }

    private boolean isCategoria(Token token){
        return token.palavra().equals("VAR") ||
                token.palavra().equals("LABEL") ||
                token.palavra().equals("CONST") ||
                token.palavra().equals("PROCEDURE") ||
                token.palavra().equals("PROGRAM");
    }
}
