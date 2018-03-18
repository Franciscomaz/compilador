/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.controllers;

import compilador.scanner.ErroLexico;
import compilador.scanner.Leitor;
import compilador.scanner.Scanner;
import compilador.utils.LeitorDeArquivo;
import compilador.view.EditorDeTexto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EditorController implements ActionListener {

    private final EditorDeTexto editor;

    public EditorController(EditorDeTexto editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            verificarComando(e.getActionCommand());
        } catch (IOException|ErroLexico ex) {
            JOptionPane.showMessageDialog(editor, ex.getMessage());
        }
    }

    private void verificarComando(String comando) throws IOException, ErroLexico {
        switch (comando) {
            case "Abrir":
                abrir("");
                break;
            case "Salvar":
                salvar("", "");
                break;
            case "SalvarComo":
                salvar("", "");
                break;
            case "Formatar":
                break;
            case "Compilar":
                compilar(editor.getTexto());
                break;
            case "Fechar":
                editor.dispose();
                break;
            default:
                break;
        }
    }

    public String abrir(String path) throws IOException {
        return LeitorDeArquivo.ler(path);
    }

    public void salvar(String path, String conteudo) throws IOException {
        try {
            new FileWriter(path).write(conteudo);
        } catch (IOException ex) {
            throw new IOException("Não foi possível salvar o arquivo.");
        }
    }

    public String salvarComo(String path) throws IOException {
        return LeitorDeArquivo.ler(path);
    }

    public void formatar(String path) throws IOException {
        
    }

    public void compilar(String path) throws ErroLexico  {
        System.out.println("-----------Pilha------------");
        new Scanner(new Leitor(path)).geTokens().forEach(System.out::println);
    }
}
