package compilador.parser;

import compilador.simbolo.Simbolo;
import compilador.simbolo.TabelaDeSimbolos;
import compilador.token.Token;
import java.util.Stack;

public class Derivacao {
    private final Stack<String> pilhaDeProducoes;
    private final MatrizDeParsing matrizDeParsing;
    private final TabelaDeSimbolos tabelaDeSimbolos;
    
    public Derivacao(String producaoInicial){
        pilhaDeProducoes = new Stack<>();
        pilhaDeProducoes.push(producaoInicial);
        matrizDeParsing = new MatrizDeParsing();
        tabelaDeSimbolos = new TabelaDeSimbolos();
    }
    
    public void derivar(Token token) throws ErroSintatico{
        String derivacao = matrizDeParsing
                .getDerivacao(tabelaDeSimbolos.getSimbolo(removerProducao()), token);
        if(derivacao == null){
            throw new ErroSintatico(token);
        }
        if(derivacao.equals("NULL")){
            removerProducao();
            return;
        }
        adicionarProducoes(separarEmProducoes(derivacao));
    }
    
    public Simbolo proximoSimbolo(){
        return tabelaDeSimbolos.getSimbolo(pilhaDeProducoes.peek());
    }

    public String removerProducao(){
        return pilhaDeProducoes.pop();
    }
    
    public boolean isEmpty(){
        return pilhaDeProducoes.isEmpty();
    }
    
    private void adicionarProducoes(String[] producoesArray){
        for (int i = producoesArray.length - 1; 0 <= i; i--) {
            pilhaDeProducoes.push(producoesArray[i]);
        }
    }
    
    private String[] separarEmProducoes(String derivacao){
        return derivacao.split("\\|");
    }
}
