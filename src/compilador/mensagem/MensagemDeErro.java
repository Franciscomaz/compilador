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
public class MensagemDeErro implements Mensagem{
    private final String mensagem;

    public MensagemDeErro(String mensagem) {
        this.mensagem = mensagem;
    }
    
    @Override
    public String conteudo() {
        return mensagem;
    }

    @Override
    public Color cor() {
        return Color.RED;
    }
}
