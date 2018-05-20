package compilador.semantico;

import compilador.simbolo.Identificador;
import compilador.token.Token;

public class AnalisadorSemantico {
    private int nivel;
    private String acao;
    private String categoria;
    private ListaDeIdentificadores identificadores;

    public AnalisadorSemantico() {
        this.nivel = -1;
        this.acao = "declaracao";
        this.identificadores = new ListaDeIdentificadores();
    }

    public void lerToken(Token token) throws ErroSemantico {
        if (token.isIdentificador()) {
            var identificador = new Identificador(token, nivel, categoria);
            if (acao.equals("atribuicao")) {
                if (!identificadores.contem(identificador)) {
                    throw new ErroSemantico("Simbolo não declarado", token);
                }
            } else{
                if (identificadores.contem(identificador)){
                    throw new ErroSemantico("Simbolo já declarado", token);
                }
            }
            identificadores.adicionar(identificador);
        } else if (token.isCategoria()) {
            categoria = token.palavra();
        } else if (token.palavra().equals("BEGIN")) {
            acao = "atribuicao";
        } else if (token.palavra().equals("PROCEDURE")) {
            acao = "declaracao";
        }
        verificarNivel(token);
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
}
