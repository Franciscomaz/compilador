package compilador.parser;

import java.util.Stack;

public class Derivacao {
    Stack<String> derivados;
    
    public Derivacao(String derivacao) throws Exception {
        if(derivacao.equals("NULL")){
            throw new Exception();
        }
        derivados = new Stack<>();
        adicionarNaPilha(derivacao.split("\\|"));
    }
    
    private void adicionarNaPilha(String[] derivacao){
        for(String derivado : derivacao){
            derivados.push(derivado);
        }
    }
    
    public String proximoDerivado(){
        return derivados.pop();
    }
}
