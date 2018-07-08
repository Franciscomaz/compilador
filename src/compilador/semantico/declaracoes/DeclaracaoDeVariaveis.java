package compilador.semantico.declaracoes;

import compilador.Identificador.Variavel;
import compilador.Identificador.tipo.Tipo;
import compilador.Identificador.tipo.TipoFactory;
import compilador.semantico.ErroSemantico;
import compilador.semantico.Escopo.Escopo;
import compilador.token.Token;

import java.util.*;

public class DeclaracaoDeVariaveis extends DeclaracaoDeIdentificadores {
    private Set<Variavel> variaveis = new HashSet<>();

    public DeclaracaoDeVariaveis(Stack<Token> tokens) {
        super(tokens);
    }

    @Override
    protected void ler(Token token, Escopo escopo) throws ErroSemantico {
        if(token.isIdentificador()){
            Variavel variavel = new Variavel(token);
            escopo.adicionarIdentificador(variavel);
            variaveis.add(variavel);
        } else if (token.isTipo()){
            Tipo tipo = TipoFactory.criar(token.palavra(), tokens);
            System.out.println(tipo);
            variaveis.stream().map(variavel -> variavel.setTipo(tipo));
        }
    }
}
