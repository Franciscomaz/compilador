package compilador.parser;

import java.util.Stack;

public class Derivacao {
    Stack<String> producoes;
    
    public Derivacao(String derivacao) throws Exception {
        if(derivacao.equals("NULL")){
            throw new Exception();
        }
        producoes = new Stack<>();
        adicionarNaPilha(derivacao.split("\\|"));
    }
    
    private void adicionarNaPilha(String[] derivacao){
        for(String derivado : derivacao){
            producoes.push(derivado);
        }
    }
    
    public String proximaProducao(){
        return producoes.pop();
    }
}
