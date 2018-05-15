package compilador.simbolo;

import java.util.HashMap;

public class TabelaDeSimbolos {

    private final HashMap<String, Integer> terminais;
    private final HashMap<String, Integer> naoTerminais;

    public TabelaDeSimbolos() {
        terminais = TabelaTerminais.getInstance();
        naoTerminais = TabelaNaoTerminais.getInstance();
    }

    public Simbolo getSimbolo(String simbolo) {
        return new Simbolo(codigo(simbolo.toUpperCase()), simbolo.toUpperCase());
    }

    public Simbolo getSimboloTerminal(String simbolo) {
        return new Simbolo(terminais.get(simbolo.toUpperCase()), simbolo.toUpperCase());
    }

    public Simbolo getSimboloComValor(String simbolo, String valor) {
        return new Simbolo(terminais.get(simbolo.toUpperCase()), valor.toUpperCase());
    }

    private int codigo(String simbolo) {
        return terminais.containsKey(simbolo.toUpperCase())
                ? terminais.get(simbolo)
                : naoTerminais.get(simbolo);
    }

    public boolean contemTerminal(String terminal) {
        return terminais.containsKey(terminal.toUpperCase());
    }

    private static class TabelaTerminais {

        private static HashMap<String, Integer> instance;

        static HashMap<String, Integer> getInstance() {
            if (instance != null) {
                return instance;
            }
            instance = new HashMap<>();
            instance.put("PROGRAM", 1);
            instance.put("LABEL", 2);
            instance.put("CONST", 3);
            instance.put("VAR", 4);
            instance.put("PROCEDURE", 5);
            instance.put("BEGIN", 6);
            instance.put("END", 7);
            instance.put("INTEGER", 8);
            instance.put("ARRAY", 9);
            instance.put("OF", 10);
            instance.put("CALL", 11);
            instance.put("GOTO", 12);
            instance.put("IF", 13);
            instance.put("THEN", 14);
            instance.put("ELSE", 15);
            instance.put("WHILE", 16);
            instance.put("DO", 17);
            instance.put("REPEAT", 18);
            instance.put("UNTIL", 19);
            instance.put("READLN", 20);
            instance.put("WRITELN", 21);
            instance.put("OR", 22);
            instance.put("AND", 23);
            instance.put("NOT", 24);
            instance.put("IDENTIFICADOR", 25);
            instance.put("INTEIRO", 26);
            instance.put("FOR", 27);
            instance.put("TO", 28);
            instance.put("CASE", 29);
            instance.put("+", 30);
            instance.put("-", 31);
            instance.put("*", 32);
            instance.put("/", 33);
            instance.put("[", 34);
            instance.put("]", 35);
            instance.put("(", 36);
            instance.put(")", 37);
            instance.put(":=", 38);
            instance.put(":", 39);
            instance.put("=", 40);
            instance.put(">", 41);
            instance.put(">=", 42);
            instance.put("<", 43);
            instance.put("<=", 44);
            instance.put("<>", 45);
            instance.put(",", 46);
            instance.put(";", 47);
            instance.put("LITERAL", 48);
            instance.put(".", 49);
            instance.put("..", 50);
            instance.put("$", 51);
            return instance;
        }
    }

    private static class TabelaNaoTerminais {

        private static HashMap<String, Integer> instance;

        static HashMap<String, Integer> getInstance() {
            if (instance != null) {
                return instance;
            }
            instance = new HashMap<>();
            instance.put("PROGRAMA", 52);
            instance.put("BLOCO", 53);
            instance.put("DCLROT", 54);
            instance.put("LID", 55);
            instance.put("REPIDENT", 56);
            instance.put("DCLCONST", 57);
            instance.put("LDCONST", 58);
            instance.put("DCLVAR", 59);
            instance.put("LDVAR", 60);
            instance.put("TIPO", 61);
            instance.put("DCLPROC", 62);
            instance.put("DEFPAR", 63);
            instance.put("CORPO", 64);
            instance.put("REPCOMANDO", 65);
            instance.put("COMANDO", 66);
            instance.put("RCOMID", 67);
            instance.put("RVAR", 68);
            instance.put("PARAMETROS", 69);
            instance.put("REPPAR", 70);
            instance.put("ELSEPARTE", 71);
            instance.put("VARIAVEL", 72);
            instance.put("VARIAVEL1", 73);
            instance.put("REPVARIAVEL", 74);
            instance.put("ITEMSAIDA", 75);
            instance.put("REPITEM", 76);
            instance.put("EXPRESSAO", 77);
            instance.put("REPEXPSIMP", 78);
            instance.put("EXPSIMP", 79);
            instance.put("REPEXP", 80);
            instance.put("TERMO", 81);
            instance.put("REPTERMO", 82);
            instance.put("FATOR", 83);
            instance.put("CONDCASE", 84);
            instance.put("CONTCASE", 85);
            instance.put("RPINTEIRO", 86);
            instance.put("SEM EFE", 87);
            return instance;
        }
    }
}
