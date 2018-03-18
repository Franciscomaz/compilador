/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.controllers;

import compilador.scanner.ErroLexico;
import compilador.scanner.Leitor;
import compilador.scanner.Scanner;
import compilador.utils.ArquivoUtils;
import compilador.view.EditorDeTexto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

    private void formatar(String path) throws IOException {

    }

    private void compilar() throws ErroLexico {
        System.out.println("-----------Pilha------------");
        new Scanner(new Leitor(editor.getTexto())).geTokens().forEach(System.out::println);
    }

    private void sair() {
        editor.dispose();
    }
}
