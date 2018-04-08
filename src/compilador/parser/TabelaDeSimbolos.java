/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.parser;

import compilador.token.Token;
import java.util.HashMap;

/**
 *
 * @author comp15
 */
public class TabelaDeSimbolos {

    private final HashMap<String, Integer> tabelaDeSimbolos;

    public TabelaDeSimbolos() {
        tabelaDeSimbolos = new HashMap<>();
        tabelaDeSimbolos.put("PROGRAMA", 52);
        tabelaDeSimbolos.put("BLOCO", 53);
        tabelaDeSimbolos.put("DCLROT", 54);
        tabelaDeSimbolos.put("LID", 55);
        tabelaDeSimbolos.put("REPIDENT", 56);
        tabelaDeSimbolos.put("DCLCONST", 57);
        tabelaDeSimbolos.put("LDCONST", 58);
        tabelaDeSimbolos.put("DCLVAR", 59);
        tabelaDeSimbolos.put("LDVAR", 60);
        tabelaDeSimbolos.put("TIPO", 61);
        tabelaDeSimbolos.put("DCLPROC", 62);
        tabelaDeSimbolos.put("DEFPAR", 63);
        tabelaDeSimbolos.put("CORPO", 64);
        tabelaDeSimbolos.put("REPCOMANDO", 65);
        tabelaDeSimbolos.put("COMANDO", 66);
        tabelaDeSimbolos.put("RCOMID", 67);
        tabelaDeSimbolos.put("RVAR", 68);
        tabelaDeSimbolos.put("PARAMETROS", 69);
        tabelaDeSimbolos.put("REPPAR", 70);
        tabelaDeSimbolos.put("ELSEPARTE", 71);
        tabelaDeSimbolos.put("VARIAVEL", 72);
        tabelaDeSimbolos.put("VARIAVEL1", 73);
        tabelaDeSimbolos.put("REPVARIAVEL", 74);
        tabelaDeSimbolos.put("ITEMSAIDA", 75);
        tabelaDeSimbolos.put("REPITEM", 76);
        tabelaDeSimbolos.put("EXPRESSAO", 77);
        tabelaDeSimbolos.put("REPEXPSIMP", 78);
        tabelaDeSimbolos.put("EXPSIMP", 79);
        tabelaDeSimbolos.put("REPEXP", 80);
        tabelaDeSimbolos.put("TERMO", 81);
        tabelaDeSimbolos.put("REPTERMO", 82);
        tabelaDeSimbolos.put("FATOR", 83);
        tabelaDeSimbolos.put("CONDCASE", 84);
        tabelaDeSimbolos.put("CONTCASE", 85);
        tabelaDeSimbolos.put("RPINTEIRO", 86);
        tabelaDeSimbolos.put("SEM EFE", 87);
    }

    public int getCodigo(Token token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
