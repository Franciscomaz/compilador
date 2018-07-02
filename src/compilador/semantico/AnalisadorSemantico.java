package compilador.semantico;

import compilador.Identificador.*;
import compilador.Identificador.tipo.Array;
import compilador.Identificador.tipo.Inteiro;
import compilador.Identificador.tipo.Tipo;
import compilador.Identificador.tipo.TipoFactory;
import compilador.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AnalisadorSemantico {
    private int nivel;
    private String categoria;
    private final Stack<Token> tokens;
    private TabelaDeIdentificadores<Identificador> identificadores;

    public AnalisadorSemantico(Stack<Token> tokens) {
        this.nivel = -1;
        this.tokens = tokens;
        this.identificadores = new TabelaDeIdentificadores<>();
    }

    public void lerTokens() throws ErroSemantico {
        while (!tokens.empty()) {
            Token token = tokens.pop();
            verificarNivel(token);
            if (token.isCategoria()) {
                categoria = token.palavra();
            }
            if (categoria != null) {
                lerDeclaracao(token);
            } else {
                lerAtribuicao(token);
            }
        }
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
                throw new ErroSemantico("identificador não declarado", token);
            }
        }
        switch (token.palavra()) {
            case "CALL":
                lerChamada();
                break;
        }
    }

    private void lerProgram() throws ErroSemantico {
        identificadores.adicionar(new Procedure(tokens.pop(), nivel));
    }

    private void lerVar() throws ErroSemantico {
        List<Variavel> variaveisSemTipo = new ArrayList<>();
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                var variavel = new Variavel(token, nivel);
                variaveisSemTipo.add(variavel);
            } else if (token.isTipo()) {
                tiparIdentificadores(variaveisSemTipo, lerTipo(TipoFactory.criar(token.palavra())));
            } else if (pararLeitura(token)) {
                break;
            }
            token = tokens.pop();
        }
    }

    private Tipo lerTipo(Tipo tipo) throws ErroSemantico {
        if(tipo.nome().equals("inteiro")) return tipo;
        Array array  = (Array) tipo;
        tokens.pop();
        array.setInicio(Integer.parseInt(tokens.pop().palavra()));
        tokens.pop();
        array.setFinal(Integer.parseInt(tokens.pop().palavra()));
        tokens.pop();
        tokens.pop();
        array.setTipo(TipoFactory.criar(tokens.pop().palavra()));
        return array;
    }

    private void lerConstante() throws ErroSemantico {
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                identificadores.adicionar(new Constante(token, nivel));
            } else if (pararLeitura(token)) {
                break;
            }
            token = tokens.pop();
        }
    }

    private void lerProcedure() throws ErroSemantico {
        Token token = tokens.pop();
        List<Parametro> parametros = new ArrayList<>();
        Procedure procedure = new Procedure(token, nivel);
        while (!token.palavra().equals(";")) {
            token = tokens.pop();
            if (token.isIdentificador()) {
                var parametro = new Parametro(token, nivel);
                parametros.add(parametro);
                procedure.adicionarParametro(parametro);
            } else if (token.isTipo()) {
                tiparIdentificadores(parametros, TipoFactory.criar(token.palavra()));
            }
        }
        identificadores.adicionar(procedure);
    }

    private void lerRotulo() throws ErroSemantico {
        Token token = tokens.pop();
        while (!tokens.empty()) {
            if (token.isIdentificador()) {
                identificadores.adicionar(new Label(token, nivel));
            } else if (pararLeitura(token)) {
                break;
            }
            token = tokens.pop();
        }
    }

    private void lerChamada() throws ErroSemantico {
        int posicao = 0;
        Token token = tokens.pop();
        var procedure = (Procedure) identificadores.buscar(token.palavra());
        if (procedure == null) {
            throw new ErroSemantico("procedure não declarada ", token);
        }
        while (!token.palavra().equals(";")) {
            token = tokens.pop();
            if (token.isIdentificador()) {
                var parametro = procedure.getParametro(posicao);
                try{
                    var temp = (Variavel)identificadores.buscar(token.palavra());
                    if (!parametro.getTipo().equals(temp.getTipo())) {
                        throw new ErroSemantico("parametro não possui o mesmo tipo (" + parametro.getTipo() + ")");
                    }
                } catch (ClassCastException exception){
                    throw new ErroSemantico("parametro não possui a mesma categoria", token);
                }
            }
        }
    }


    private void verificarNivel(Token token) {
        final String palavra = token.palavra();
        if (palavra.equals("PROGRAM") || palavra.equals("PROCEDURE") || palavra.equals("IF") || palavra.equals("WHILE") || palavra.equals("FOR")) {
            nivel++;
            System.out.println("++"+nivel);
        } else if (palavra.equals("END")) {
            identificadores.removerPeloNivel(nivel);
            nivel--;
            System.out.println("-- " + nivel);
        }
    }

    public void tiparIdentificadores(List<? extends Variavel> identificadoresSemTipo, Tipo tipo) throws ErroSemantico {
        identificadoresSemTipo.forEach(identificador -> identificador.setTipo(tipo));
        for (Identificador identificador : identificadoresSemTipo) {
            identificadores.adicionar(identificador);
        }
        identificadoresSemTipo.clear();
    }

    private boolean pararLeitura(Token token) {
        if (token.isCategoria()) {
            categoria = token.palavra();
            tokens.push(token);
            return true;
        } else if (token.palavra().equals("BEGIN")) {
            categoria = null;
            return true;
        }
        return false;
    }
}
