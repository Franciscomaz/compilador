/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.token;

/**
 *
 * @author comp1
 */
public class Token {
    private final int codigo;
    private final String lexema;

    public Token(int codigo, String lexema) {
        this.codigo = codigo;
        this.lexema = lexema;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getLexema() {
        return lexema;
    }

    @Override
    public String toString() {
        return codigo + "|" + lexema;
    }
}
