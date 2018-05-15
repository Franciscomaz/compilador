/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.parser;

import compilador.token.Token;

/**
 *
 * @author comp15
 */
public class ErroSintatico extends Exception{
    public ErroSintatico() {
    }

    public ErroSintatico(Token token) {
        super("Erro sintático linha " + token.linha() + ", coluna " + token.coluna() + ": '" + token + "'");
    }    
    
    public ErroSintatico(String message) {
        super(message);
    }
}
