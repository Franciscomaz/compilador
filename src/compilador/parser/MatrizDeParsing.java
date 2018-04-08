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
public class MatrizDeParsing {

    private final String[][] matrizDeParsing = new String[190][1];

    public MatrizDeParsing() {
        matrizDeParsing[52][1] = "PROGRAM|IDENTIFICADOR|;|BLOCO|.";
        matrizDeParsing[53][2] = "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO";
        matrizDeParsing[53][3] = "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO";
        matrizDeParsing[53][4] = "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO";
        matrizDeParsing[53][5] = "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO";
        matrizDeParsing[53][6] = "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO";
        matrizDeParsing[54][2] = "LABEL|LID|;";
        matrizDeParsing[54][3] = "NULL";
        matrizDeParsing[54][4] = "NULL";
        matrizDeParsing[54][5] = "NULL";
        matrizDeParsing[54][6] = "NULL";
        matrizDeParsing[55][25] = "IDENTIFICADOR|REPIDENT";
        matrizDeParsing[56][39] = "NULL";
        matrizDeParsing[56][46] = ",|IDENTIFICADOR|REPIDENT";
        matrizDeParsing[56][47] = "NULL";
        matrizDeParsing[57][3] = "CONST|IDENTIFICADOR|=|INTEIRO|;|LDCONST";
        matrizDeParsing[57][4] = "NULL";
        matrizDeParsing[57][5] = "NULL";
        matrizDeParsing[57][6] = "NULL";
        matrizDeParsing[58][4] = "NULL";
        matrizDeParsing[58][5] = "NULL";
        matrizDeParsing[58][6] = "NULL";
        matrizDeParsing[58][25] = "IDENTIFICADOR|=|INTEIRO|;|LDCONST";
        matrizDeParsing[59][4] = "VAR|LID|:|TIPO|;|LDVAR";
        matrizDeParsing[59][5] = "NULL";
        matrizDeParsing[59][6] = "NULL";
        matrizDeParsing[60][5] = "NULL";
        matrizDeParsing[60][6] = "NULL";
        matrizDeParsing[60][25] = "LID|:|TIPO|;|LDVAR";
        matrizDeParsing[61][8] = "INTEGER";
        matrizDeParsing[61][9] = "ARRAY|[|INTEIRO|..|INTEIRO|]|OF|INTEGER";
        matrizDeParsing[62][5] = "PROCEDURE|IDENTIFICADOR|DEFPAR|;|BLOCO|;|DCLPROC";
        matrizDeParsing[62][6] = "NULL";
        matrizDeParsing[63][36] = "(|LID|:|INTEGER|)";
        matrizDeParsing[63][39] = "NULL";
        matrizDeParsing[64][6] = "BEGIN|COMANDO|REPCOMANDO|END";
        matrizDeParsing[65][7] = "NULL";
        matrizDeParsing[65][47] = ";|COMANDO|REPCOMANDO";
        matrizDeParsing[66][6] = "CORPO";
        matrizDeParsing[66][7] = "NULL";
        matrizDeParsing[66][11] = "CALL|IDENTIFICADOR|PARAMETROS";
        matrizDeParsing[66][12] = "GOTO|IDENTIFICADOR";
        matrizDeParsing[66][13] = "IF|EXPRESSAO|THEN|COMANDO|ELSEPARTE";
        matrizDeParsing[66][15] = "NULL";
        matrizDeParsing[66][16] = "WHILE|EXPRESSAO|DO|COMANDO";
        matrizDeParsing[66][18] = "REPEAT|COMANDO|UNTIL|EXPRESSAO";
        matrizDeParsing[66][19] = "NULL";
        matrizDeParsing[66][20] = "READLN|(|VARIAVEL|REPVARIAVEL|)";
        matrizDeParsing[66][21] = "WRITELN|(|ITEMSAIDA|REPITEM|)";
        matrizDeParsing[66][25] = "IDENTIFICADOR|RCOMID";
        matrizDeParsing[66][27] = "FOR|IDENTIFICADOR|:=|EXPRESSAO|TO|EXPRESSAO|DO|COMANDO";
        matrizDeParsing[66][29] = "CASE|EXPRESSAO|OF|CONDCASE|END";
        matrizDeParsing[66][47] = "NULL";
        matrizDeParsing[67][34] = "RVAR|:=|EXPRESSAO";
        matrizDeParsing[67][38] = "RVAR|:=|EXPRESSAO";
        matrizDeParsing[67][39] = ":|COMANDO";
        matrizDeParsing[68][34] = "[|EXPRESSAO|]";
        matrizDeParsing[68][38] = "NULL";
        matrizDeParsing[69][7] = "NULL";
        matrizDeParsing[69][15] = "NULL";
        matrizDeParsing[69][19] = "NULL";
        matrizDeParsing[69][36] = "(|EXPRESSAO|REPPAR|)";
        matrizDeParsing[69][47] = "NULL";
        matrizDeParsing[70][37] = "NULL";
        matrizDeParsing[70][46] = ",|EXPRESSAO|REPPAR";
        matrizDeParsing[71][7] = "NULL";
        matrizDeParsing[71][15] = "ELSE|COMANDO";
        matrizDeParsing[71][19] = "NULL";
        matrizDeParsing[71][47] = "NULL";
        matrizDeParsing[72][25] = "IDENTIFICADOR|VARIAVEL1";
        matrizDeParsing[73][7] = "NULL";
        matrizDeParsing[73][10] = "NULL";
        matrizDeParsing[73][14] = "NULL";
        matrizDeParsing[73][15] = "NULL";
        matrizDeParsing[73][17] = "NULL";
        matrizDeParsing[73][19] = "NULL";
        matrizDeParsing[73][22] = "NULL";
        matrizDeParsing[73][23] = "NULL";
        matrizDeParsing[73][28] = "NULL";
        matrizDeParsing[73][30] = "NULL";
        matrizDeParsing[73][31] = "NULL";
        matrizDeParsing[73][32] = "NULL";
        matrizDeParsing[73][33] = "NULL";
        matrizDeParsing[73][34] = "[|EXPRESSAO|]";
        matrizDeParsing[73][35] = "NULL";
        matrizDeParsing[73][37] = "NULL";
        matrizDeParsing[73][40] = "NULL";
        matrizDeParsing[73][41] = "NULL";
        matrizDeParsing[73][42] = "NULL";
        matrizDeParsing[73][43] = "NULL";
        matrizDeParsing[73][44] = "NULL";
        matrizDeParsing[73][45] = "NULL";
        matrizDeParsing[73][46] = "NULL";
        matrizDeParsing[73][47] = "NULL";
        matrizDeParsing[74][37] = "NULL";
        matrizDeParsing[74][46] = ",|VARIAVEL|REPVARIAVEL";
        matrizDeParsing[75][24] = "EXPRESSAO";
        matrizDeParsing[75][25] = "EXPRESSAO";
        matrizDeParsing[75][26] = "EXPRESSAO";
        matrizDeParsing[75][30] = "EXPRESSAO";
        matrizDeParsing[75][31] = "EXPRESSAO";
        matrizDeParsing[75][36] = "EXPRESSAO";
        matrizDeParsing[75][48] = "LITERAL";
        matrizDeParsing[76][37] = "NULL";
        matrizDeParsing[76][46] = ",|ITEMSAIDA|REPITEM";
        matrizDeParsing[77][24] = "EXPSIMP|REPEXPSIMP";
        matrizDeParsing[77][25] = "EXPSIMP|REPEXPSIMP";
        matrizDeParsing[77][26] = "EXPSIMP|REPEXPSIMP";
        matrizDeParsing[77][30] = "EXPSIMP|REPEXPSIMP";
        matrizDeParsing[77][31] = "EXPSIMP|REPEXPSIMP";
        matrizDeParsing[77][36] = "EXPSIMP|REPEXPSIMP";
        matrizDeParsing[78][7] = "NULL";
        matrizDeParsing[78][10] = "NULL";
        matrizDeParsing[78][14] = "NULL";
        matrizDeParsing[78][15] = "NULL";
        matrizDeParsing[78][17] = "NULL";
        matrizDeParsing[78][19] = "NULL";
        matrizDeParsing[78][28] = "NULL";
        matrizDeParsing[78][35] = "NULL";
        matrizDeParsing[78][37] = "NULL";
        matrizDeParsing[78][40] = "=|EXPSIMP";
        matrizDeParsing[78][41] = ">|EXPSIMP";
        matrizDeParsing[78][42] = ">=|EXPSIMP";
        matrizDeParsing[78][43] = "<|EXPSIMP";
        matrizDeParsing[78][44] = "<=|EXPSIMP";
        matrizDeParsing[78][45] = "<>|EXPSIMP";
        matrizDeParsing[78][46] = "NULL";
        matrizDeParsing[78][47] = "NULL";
        matrizDeParsing[79][24] = "TERMO|REPEXP";
        matrizDeParsing[79][25] = "TERMO|REPEXP";
        matrizDeParsing[79][26] = "TERMO|REPEXP";
        matrizDeParsing[79][30] = "+|TERMO|REPEXP";
        matrizDeParsing[79][31] = "-|TERMO|REPEXP";
        matrizDeParsing[79][36] = "TERMO|REPEXP";
        matrizDeParsing[80][7] = "NULL";
        matrizDeParsing[80][10] = "NULL";
        matrizDeParsing[80][14] = "NULL";
        matrizDeParsing[80][15] = "NULL";
        matrizDeParsing[80][17] = "NULL";
        matrizDeParsing[80][19] = "NULL";
        matrizDeParsing[80][22] = "OR|TERMO|REPEXP";
        matrizDeParsing[80][28] = "NULL";
        matrizDeParsing[80][30] = "+|TERMO|REPEXP";
        matrizDeParsing[80][31] = "-|TERMO|REPEXP";
        matrizDeParsing[80][35] = "NULL";
        matrizDeParsing[80][37] = "NULL";
        matrizDeParsing[80][40] = "NULL";
        matrizDeParsing[80][41] = "NULL";
        matrizDeParsing[80][42] = "NULL";
        matrizDeParsing[80][43] = "NULL";
        matrizDeParsing[80][44] = "NULL";
        matrizDeParsing[80][45] = "NULL";
        matrizDeParsing[80][46] = "NULL";
        matrizDeParsing[80][47] = "NULL";
        matrizDeParsing[81][24] = "FATOR|REPTERMO";
        matrizDeParsing[81][25] = "FATOR|REPTERMO";
        matrizDeParsing[81][26] = "FATOR|REPTERMO";
        matrizDeParsing[81][36] = "FATOR|REPTERMO";
        matrizDeParsing[82][7] = "NULL";
        matrizDeParsing[82][10] = "NULL";
        matrizDeParsing[82][14] = "NULL";
        matrizDeParsing[82][15] = "NULL";
        matrizDeParsing[82][17] = "NULL";
        matrizDeParsing[82][19] = "NULL";
        matrizDeParsing[82][22] = "NULL";
        matrizDeParsing[82][23] = "AND|FATOR|REPTERMO";
        matrizDeParsing[82][28] = "NULL";
        matrizDeParsing[82][30] = "NULL";
        matrizDeParsing[82][31] = "NULL";
        matrizDeParsing[82][32] = "*|FATOR|REPTERMO";
        matrizDeParsing[82][33] = "/|FATOR|REPTERMO";
        matrizDeParsing[82][35] = "NULL";
        matrizDeParsing[82][37] = "NULL";
        matrizDeParsing[82][40] = "NULL";
        matrizDeParsing[82][41] = "NULL";
        matrizDeParsing[82][42] = "NULL";
        matrizDeParsing[82][43] = "NULL";
        matrizDeParsing[82][44] = "NULL";
        matrizDeParsing[82][45] = "NULL";
        matrizDeParsing[82][46] = "NULL";
        matrizDeParsing[82][47] = "NULL";
        matrizDeParsing[83][24] = "NOT|FATOR";
        matrizDeParsing[83][25] = "VARIAVEL";
        matrizDeParsing[83][26] = "INTEIRO";
        matrizDeParsing[83][36] = "(|EXPRESSAO|)";
        matrizDeParsing[84][26] = "INTEIRO|RPINTEIRO|:|COMANDO|CONTCASE";
        matrizDeParsing[85][7] = "NULL";
        matrizDeParsing[85][47] = ";|CONDCASE";
        matrizDeParsing[86][39] = "NULL";
        matrizDeParsing[86][46] = ",|INTEIRO|RPINTEIRO";
    }
    
    public String getDerivacao(Token token){
        return matrizDeParsing[new TabelaDeSimbolos().getCodigo(token)][token.getCodigo()];
    }
}
