/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.controllers;

import compilador.mensagem.MensagemDeErro;
import compilador.mensagem.MensagemDeSucesso;
import compilador.semantico.AnalisadorSemantico;
import compilador.sintatico.ErroSintatico;
import compilador.sintatico.Parser;
import compilador.lexico.ErroLexico;
import compilador.lexico.Leitor;
import compilador.lexico.Scanner;
import compilador.semantico.ErroSemantico;
import compilador.token.Token;
import compilador.utils.ArquivoUtils;
import compilador.view.EditorDeTexto;
import compilador.lexico.Tabela;
import compilador.view.Console;
import compilador.view.TabelaView;

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
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(editor, ex.getMessage());
        }
    }

    private void verificarComando(ActionEvent e) throws IOException {
        switch (e.getActionCommand()) {
            case "Novo":
                novo();
                break;
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

    private void novo() {
        editor.setTexto("");
        filePath = null;
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

    private void compilar() {
        try {
            Stack<Token> pilha = new Scanner(new Leitor(editor.getTexto())).analisar();
            editor.adicionarTabela(new TabelaView(new Tabela(pilha)));
            editor.adicionarConsole(new Console(new MensagemDeSucesso()));
            new Parser(inverterPilha(pilha)).analisar();
            new AnalisadorSemantico(inverterPilha(pilha)).lerTokens();
        } catch (ErroLexico | ErroSintatico | ErroSemantico e) {
            editor.adicionarConsole(new Console(new MensagemDeErro(e.getMessage())));
        }
    }

    private Stack<Token> inverterPilha(Stack<Token> pilha) {
        var temp = (Stack<Token>) pilha.clone();
        var pilhaInvertida = new Stack<Token>();
        while (!temp.empty()) {
            pilhaInvertida.push(temp.pop());
        }
        return pilhaInvertida;
    }

    private void sair() {
        editor.dispose();
    }
}
