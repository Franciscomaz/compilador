package compilador.simbolo;

import java.util.HashMap;

public class TabelaDeSimbolos {

    private static HashMap<String, Integer> terminais;
    private static HashMap<String, Integer> naoTerminais;

    public TabelaDeSimbolos() {
        terminais = TabelasSingleton.terminaisInstance();
        naoTerminais = TabelasSingleton.naoTerminaisInstance();
    }

    public Simbolo getSimbolo(String palavra) {
        return new Simbolo(getCodigo(palavra), palavra);
    }

    public Simbolo getSimboloTerminal(String palavra) {
        return new Simbolo(terminais.get(palavra), palavra);
    }

    public int getCodigo(String simbolo) {
        return terminais.containsKey(simbolo)
                ? terminais.get(simbolo)
                : naoTerminais.get(simbolo);
    }

    public boolean contemTerminal(String terminal) {
        return naoTerminais.containsKey(terminal) || terminais.containsKey(terminal);
    }

    private static class TabelasSingleton {

        public static HashMap<String, Integer> terminaisInstance() {
            if (terminais != null) {
                return terminais;
            }
            terminais = new HashMap<>();
            terminais.put("Program", 1);
            terminais.put("Label", 2);
            terminais.put("Const", 3);
            terminais.put("Var", 4);
            terminais.put("Procedure", 5);
            terminais.put("Begin", 6);
            terminais.put("End", 7);
            terminais.put("Integer", 8);
            terminais.put("Array", 9);
            terminais.put("Of", 10);
            terminais.put("Call", 11);
            terminais.put("Goto", 12);
            terminais.put("If", 13);
            terminais.put("Then", 14);
            terminais.put("Else", 15);
            terminais.put("While", 16);
            terminais.put("Do", 17);
            terminais.put("Repeat", 18);
            terminais.put("Until", 19);
            terminais.put("Readln", 20);
            terminais.put("Writeln", 21);
            terminais.put("Or", 22);
            terminais.put("And", 23);
            terminais.put("Not", 24);
            terminais.put("Identificador", 25);
            terminais.put("Inteiro", 26);
            terminais.put("For", 27);
            terminais.put("To", 28);
            terminais.put("Case", 29);
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
            terminais.put("Literal", 48);
            terminais.put(".", 49);
            terminais.put("..", 50);
            terminais.put("$", 51);
            return terminais;
        }

        public static HashMap<String, Integer> naoTerminaisInstance() {
            if (naoTerminais != null) {
                return naoTerminais;
            }
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
            return naoTerminais;
        }
    }
}
