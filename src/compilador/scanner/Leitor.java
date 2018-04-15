/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.scanner;

import java.util.Stack;

/**
 *
 * @author Chicom
 */
public class Leitor {

    private int posicao = -1;
    private final String texto;
    private final Stack<Integer> pilha;

    public Leitor(String texto) {
        this.texto = texto;
        pilha = new Stack<>();
        pilha.push(0);
    }

    public Posicao getPosicao() {
        return new Posicao(pilha);
    }

    public void rollBack() {
        if (texto.charAt(posicao--) == '\n') {
            pilha.pop();
        } else {
            pilha.push(pilha.pop() - 1);
        }
    }

    public boolean hasNext() {
        return texto.length() > posicao + 1;
    }

    public Character proximoCaracter() {
        if (!hasNext()) {
            return null;
        }
        if (texto.charAt(++posicao) == '\n') {
            pilha.push(0);
        } else {
            pilha.push(pilha.pop() + 1);
        }
        return texto.charAt(posicao);
    }

    public class Posicao {

        private final int linha;
        private final int coluna;

        private Posicao(Stack<Integer> posicao) {
            this.linha = posicao.size();
            this.coluna = posicao.peek();
        }

        public int getLinha() {
            return linha;
        }

        public int getColuna() {
            return coluna;
        }

        @Override
        public String toString() {
            return "linha " + getLinha() + ", coluna " + getColuna() + ".";
        }
    }
}
