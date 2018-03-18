/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.scanner;

/**
 *
 * @author Chicom
 */
public class ErroLexico extends Exception {

    public ErroLexico() {
    }

    public ErroLexico(String message) {
        super(message);
    }
    
    public ErroLexico(Leitor leitor){
        super("Erro léxico na " + leitor.toString());
    }
}
