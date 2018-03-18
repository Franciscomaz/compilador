/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.scanner;

/**
 *
 * @author Chicom
 */
public class Leitor {

    private int linha = 1;
    private int coluna = 0;
    private int posicao = -1;
    private final String texto;

    public Leitor(String texto) {
        this.texto = texto;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void rollBack() {
        if (--posicao > -1 && texto.charAt(posicao) == '\n') {
            linha--;
        }
        coluna--;
    }

    public boolean hasNext() {
        return texto.length() > posicao + 1;
    }

    public Character proximoCaracter() {
        if (!hasNext()) {
            return null;
        }
        if (texto.charAt(++posicao) == '\n') {
            linha++;
            coluna = 0;
        } else {
            coluna++;
        }
        return texto.charAt(posicao);
    }

    @Override
    public String toString() {
        return "linha " + linha + ", coluna " + coluna;
    }
}
