package compilador.scanner;

import java.util.Stack;

public class Leitor {

    private int posicao = -1;
    private final String texto;
    private final Stack<Integer> pilha;

    public Leitor(String texto) {
        this.texto = texto;
        this.pilha = new Stack<>();
        this.pilha.push(0);
    }

    Posicao posicao() {
        return new Posicao(pilha);
    }

    void rollBack() {
        if (texto.charAt(posicao--) == '\n') {
            pilha.pop();
        } else {
            pilha.push(pilha.pop() - 1);
        }
    }

    boolean hasNext() {
        return texto.length() > posicao + 1;
    }

    Character proximoCarater() {
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

        int linha() {
            return linha;
        }

        int coluna() {
            return coluna;
        }

        @Override
        public String toString() {
            return "Linha " + linha() + ", coluna " + coluna() + ".";
        }
    }
}
