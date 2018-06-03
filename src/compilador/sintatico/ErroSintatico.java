/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.sintatico;

import compilador.token.Token;

/**
 *
 * @author comp15
 */
public class ErroSintatico extends Exception{
    ErroSintatico(Token token) {
        super("Erro sintático: " + token + "\n" + token.posicao());
    }    
    
    ErroSintatico(String message) {
        super(message);
    }
}
