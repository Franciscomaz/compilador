/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.scanner;

import compilador.token.Token;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco
 */
public class Tabela extends AbstractTableModel {
    private final Stack<Token> pilha;
    private final List<String> titulo;
    
    public Tabela(Stack<Token> pilha) {
        this.pilha = pilha;
        titulo = new ArrayList<>();
        titulo.add("Codigo");
        titulo.add("Lexema");
    }

    @Override
    public int getRowCount() {
        return pilha.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int num) {
        return this.titulo.get(num);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if(coluna == 0){
            return pilha.get(linha).getCodigo();
        } else {
            return pilha.get(linha).getLexema();
        }
    }
}