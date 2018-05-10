/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.semantico;

import compilador.simbolo.Identificador;
import compilador.simbolo.Simbolo;
import java.util.Stack;

/**
 *
 * @author comp1
 */
public class AnalisadorSemantico {
    private String categoria;
    private Integer nivel;
    private Stack<Identificador> identificadores;

    public AnalisadorSemantico(Stack<Identificador> identificadores) {
        this.identificadores = identificadores;
    }
    
    public void lerSimbolo(Simbolo simbolo){
        if(simbolo.isIdentificador()){
            if("VAR".equals(categoria)){
                
            }
        }
    }
}
