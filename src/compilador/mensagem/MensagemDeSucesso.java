/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.mensagem;

import java.awt.Color;

/**
 *
 * @author Chicom
 */
public class MensagemDeSucesso implements Mensagem{

    @Override
    public String conteudo() {
        return "Compilado com sucesso.";
    }

    @Override
    public Color cor() {
        return new Color(0, 100, 0);
    }
}
