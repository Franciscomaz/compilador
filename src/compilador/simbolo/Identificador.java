/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.simbolo;

/**
 *
 * @author comp1
 */
public class Identificador extends Simbolo {

    private final int nivel;
    private final String tipo;
    private final String categoria;

    public Identificador(int codigo, String nome, String tipo, int nivel, String categoria) {
        super(codigo, nome);
        this.tipo = tipo;
        this.nivel = nivel;
        this.categoria = categoria;
    }
    
    public Identificador(Simbolo simbolo, String tipo, int nivel, String categoria){
        super(simbolo.getCodigo(), simbolo.getNome());
        this.tipo = tipo;
        this.nivel = nivel;
        this.categoria = categoria;
    }

    public int getNivel() {
        return nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }
}
