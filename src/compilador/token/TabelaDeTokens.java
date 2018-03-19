package compilador.token;

import java.util.HashMap;

public class TabelaDeTokens {
    private final HashMap<String, Integer> tabela;
    
    public TabelaDeTokens() {
        tabela = new HashMap<>();
        tabela.put("Program", 1);
        tabela.put("Label", 2);
        tabela.put("Const", 3);
        tabela.put("Var", 4);
        tabela.put("Procedure", 5);
        tabela.put("Begin", 6);
        tabela.put("End", 7);
        tabela.put("Integer", 8);
        tabela.put("Array", 9);
        tabela.put("Of", 10);
        tabela.put("Call", 11);
        tabela.put("Goto", 12);
        tabela.put("If", 13);
        tabela.put("Then", 14);
        tabela.put("Else", 15);
        tabela.put("While", 16);
        tabela.put("Do", 17);
        tabela.put("Repeat", 18);
        tabela.put("Until", 19);
        tabela.put("Readln", 20);
        tabela.put("Writeln", 21);
        tabela.put("Or", 22);
        tabela.put("And", 23);
        tabela.put("Not", 24);
        tabela.put("Identificador", 25);
        tabela.put("Inteiro", 26);
        tabela.put("For", 27);
        tabela.put("To", 28);
        tabela.put("Case", 29);
        tabela.put("+", 30);
        tabela.put("-", 31);
        tabela.put("*", 32);
        tabela.put("/", 33);
        tabela.put("[", 34);
        tabela.put("]", 35);
        tabela.put("(", 36);
        tabela.put(")", 37);
        tabela.put(":=", 38);
        tabela.put(":", 39);
        tabela.put("=", 40);
        tabela.put(">", 41);
        tabela.put(">=", 42);
        tabela.put("<", 43);
        tabela.put("<=", 44);
        tabela.put("<>", 45);
        tabela.put(",", 46);
        tabela.put(";", 47);
        tabela.put("Literal", 48);
        tabela.put(".", 49);
        tabela.put("..", 50);
        tabela.put("$", 51);
    }

    public int getCodigo(String palavra){
        return tabela.get(palavra);
    }
    
    public Token getIdentificador(String palavra) {
        if (tabela.containsKey(palavra)) {
            return new Token(tabela.get(palavra), palavra);
        } else {
            return new Token(tabela.get("Identificador"), palavra);
        }
    }
}
