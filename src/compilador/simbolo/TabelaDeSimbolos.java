package compilador.simbolo;

import java.util.HashMap;

public class TabelaDeSimbolos {

    private static HashMap<String, Integer> terminais;
    private static HashMap<String, Integer> naoTerminais;

    public Simbolo simbolo(String simbolo) {
        return new Simbolo(codigo(simbolo.toUpperCase()), simbolo.toUpperCase());
    }

    public Simbolo terminal(String simbolo) {
        return new Simbolo(terminais.get(simbolo.toUpperCase()), simbolo.toUpperCase());
    }

    public Simbolo terminalComValor(String simbolo, String valor) {
        return new Simbolo(terminais.get(simbolo.toUpperCase()), valor.toUpperCase());
    }

    private int codigo(String simbolo) {
        return terminais.containsKey(simbolo)
                ? terminais.get(simbolo)
                : naoTerminais.get(simbolo);
    }

    public boolean contemTerminal(String terminal) {
        return terminais.containsKey(terminal.toUpperCase());
    }

    public TabelaDeSimbolos() {
        if(terminais == null) {
            terminais = new HashMap<>();
            terminais.put("PROGRAM", 1);
            terminais.put("LABEL", 2);
            terminais.put("CONST", 3);
            terminais.put("VAR", 4);
            terminais.put("PROCEDURE", 5);
            terminais.put("BEGIN", 6);
            terminais.put("END", 7);
            terminais.put("INTEGER", 8);
            terminais.put("ARRAY", 9);
            terminais.put("OF", 10);
            terminais.put("CALL", 11);
            terminais.put("GOTO", 12);
            terminais.put("IF", 13);
            terminais.put("THEN", 14);
            terminais.put("ELSE", 15);
            terminais.put("WHILE", 16);
            terminais.put("DO", 17);
            terminais.put("REPEAT", 18);
            terminais.put("UNTIL", 19);
            terminais.put("READLN", 20);
            terminais.put("WRITELN", 21);
            terminais.put("OR", 22);
            terminais.put("AND", 23);
            terminais.put("NOT", 24);
            terminais.put("IDENTIFICADOR", 25);
            terminais.put("INTEIRO", 26);
            terminais.put("FOR", 27);
            terminais.put("TO", 28);
            terminais.put("CASE", 29);
            terminais.put("+", 30);
            terminais.put("-", 31);
            terminais.put("*", 32);
            terminais.put("/", 33);
            terminais.put("[", 34);
            terminais.put("]", 35);
            terminais.put("(", 36);
            terminais.put(")", 37);
            terminais.put(":=", 38);
            terminais.put(":", 39);
            terminais.put("=", 40);
            terminais.put(">", 41);
            terminais.put(">=", 42);
            terminais.put("<", 43);
            terminais.put("<=", 44);
            terminais.put("<>", 45);
            terminais.put(",", 46);
            terminais.put(";", 47);
            terminais.put("LITERAL", 48);
            terminais.put(".", 49);
            terminais.put("..", 50);
            terminais.put("$", 51);
        }
        if(naoTerminais == null){
            naoTerminais = new HashMap<>();
            naoTerminais.put("PROGRAMA", 52);
            naoTerminais.put("BLOCO", 53);
            naoTerminais.put("DCLROT", 54);
            naoTerminais.put("LID", 55);
            naoTerminais.put("REPIDENT", 56);
            naoTerminais.put("DCLCONST", 57);
            naoTerminais.put("LDCONST", 58);
            naoTerminais.put("DCLVAR", 59);
            naoTerminais.put("LDVAR", 60);
            naoTerminais.put("TIPO", 61);
            naoTerminais.put("DCLPROC", 62);
            naoTerminais.put("DEFPAR", 63);
            naoTerminais.put("CORPO", 64);
            naoTerminais.put("REPCOMANDO", 65);
            naoTerminais.put("COMANDO", 66);
            naoTerminais.put("RCOMID", 67);
            naoTerminais.put("RVAR", 68);
            naoTerminais.put("PARAMETROS", 69);
            naoTerminais.put("REPPAR", 70);
            naoTerminais.put("ELSEPARTE", 71);
            naoTerminais.put("VARIAVEL", 72);
            naoTerminais.put("VARIAVEL1", 73);
            naoTerminais.put("REPVARIAVEL", 74);
            naoTerminais.put("ITEMSAIDA", 75);
            naoTerminais.put("REPITEM", 76);
            naoTerminais.put("EXPRESSAO", 77);
            naoTerminais.put("REPEXPSIMP", 78);
            naoTerminais.put("EXPSIMP", 79);
            naoTerminais.put("REPEXP", 80);
            naoTerminais.put("TERMO", 81);
            naoTerminais.put("REPTERMO", 82);
            naoTerminais.put("FATOR", 83);
            naoTerminais.put("CONDCASE", 84);
            naoTerminais.put("CONTCASE", 85);
            naoTerminais.put("RPINTEIRO", 86);
            naoTerminais.put("SEM EFE", 87);
        }
    }
}
