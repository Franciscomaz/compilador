/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.controllers;

import compilador.scanner.ErroLexico;
import compilador.scanner.Leitor;
import compilador.scanner.Scanner;
import compilador.token.Token;
import compilador.utils.ArquivoUtils;
import compilador.view.EditorDeTexto;
import compilador.view.Tabela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EditorController implements ActionListener {

    private final EditorDeTexto editor;
    private String filePath = null;

    public EditorController(EditorDeTexto editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            verificarComando(e);
        } catch (IOException | ErroLexico ex) {
            JOptionPane.showMessageDialog(editor, ex.getMessage());
        }
    }

    private void verificarComando(ActionEvent e) throws IOException, ErroLexico {
        switch (e.getActionCommand()) {
            case "Abrir":
                abrir();
                break;
            case "Salvar":
                salvar();
                break;
            case "Salvar como":
                salvarComo();
                break;
            case "Formatar":
                formatar();
                break;
            case "Compilar":
                compilar();
                break;
            case "Sair":
                sair();
                break;
            default:
                break;
        }
    }

    private void abrir() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        filePath = fileChooser.getSelectedFile().getAbsolutePath();
        editor.setTexto(ArquivoUtils.ler(filePath));
    }

    private void salvar() throws IOException {
        if (filePath == null) {
            salvarComo();
            return;
        }
        ArquivoUtils.salvar(editor.getTexto(), filePath);
    }

    private void salvarComo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
        salvar();
    }

    private void formatar() throws IOException {

    }

    private void compilar() throws ErroLexico {
        System.out.println("-----------Pilha------------");
        Stack<Token> pilha = new Scanner(new Leitor(editor.getTexto())).geTokens();
        pilha.forEach(System.out::println);
        editor.setModel(new Tabela(pilha));
    }

    private void sair() {
        editor.dispose();
    }
}
