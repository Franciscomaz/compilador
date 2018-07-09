package compilador.semantico.Escopo;

import compilador.Identificador.*;
import compilador.Identificador.procedure.Parametro;
import compilador.Identificador.procedure.Parametros;
import compilador.Identificador.procedure.Procedure;
import compilador.Identificador.tipo.Inteiro;
import compilador.semantico.ErroSemantico;
import compilador.token.Token;

import java.util.Stack;

public class Execucao {
    private int nivel;
    private Escopo escopo;
    private Stack<Token> tokens;

    public Execucao(Escopo escopo, Stack<Token> tokens) {
        this.nivel = 1;
        this.escopo = escopo;
        this.tokens = tokens;
    }

    public void analisar() throws ErroSemantico {
        Token token = tokens.pop();
        while (!deveParar(token)) {
            if (token.isIdentificador()) {
                escopo.buscar(token);
            } else if (token.palavra().equals("CALL")){
                lerChamada();
            } else if (token.palavra().equals("WRITELN")){
                lerWriteln();
            } else if (token.palavra().equals("READLN")){
                lerReadln();
            }
            else if (token.codigo() == 6) {
                nivel++;
            } else if (token.codigo() == 7) {
                nivel--;
            }
            token = tokens.pop();
        }
    }

    private void lerChamada() throws ErroSemantico {
        int posicao = 0;
        int nivelDosParenteses = 0;
        Token token = tokens.pop();
        EscopoInterno procedure = escopo.getProcedure(new Procedure(token));
        Parametros parametros = procedure.getParametros();

        while(token.codigo() != 37 || nivelDosParenteses != 0){
            token = tokens.pop();
            if(token.isIdentificador()){
                Parametro parametro = parametros.buscar(posicao);
                Identificador identificador = escopo.buscar(token);
                if(isCategoriaInvalida(identificador)){
                    throw new ErroSemantico("categoria invalida", identificador);
                }
                Variavel variavel = (Variavel) identificador;
                if (!parametro.getTipo().equals(variavel.getTipo())){
                    throw new ErroSemantico("tipo invalido: " + variavel.getTipo().getClass(), variavel);
                }
            } else if (token.codigo() == 26){
                Parametro parametro = parametros.buscar(posicao);
                if(!parametro.getTipo().equals(new Inteiro())){
                    throw new ErroSemantico("tipo invalido: " + Inteiro.class, token);
                }
            }else if (token.codigo() == 36){
                nivelDosParenteses++;
            } else if (token.codigo() == 37){
                nivelDosParenteses--;
            } else if (token.codigo() == 46){
                posicao++;
            }
        }
    }

    private void lerReadln() throws ErroSemantico {
        Token token = tokens.pop();
        while(token.codigo() != 37){
            token = tokens.pop();
            if(token.isIdentificador()){
                Identificador identificador = escopo.buscar(token);
                if(isCategoriaInvalida(identificador)){
                    throw new ErroSemantico("categoria invalida", identificador);
                }
            }
        }
    }

    private void lerWriteln() throws ErroSemantico {
        Token token = tokens.pop();
        while(token.codigo() != 37){
            token = tokens.pop();
            if(token.isIdentificador()){
                Identificador identificador = escopo.buscar(token);
                if(isCategoriaInvalida(identificador)){
                    throw new ErroSemantico("categoria invalida", identificador);
                }
            }
        }
    }

    public boolean deveParar(Token token) {
        return token.codigo() == 7 && nivel == 1;
    }

    private boolean isCategoriaInvalida(Identificador identificador){
        return identificador instanceof Label || identificador instanceof Program || identificador instanceof Procedure;
    }
}
