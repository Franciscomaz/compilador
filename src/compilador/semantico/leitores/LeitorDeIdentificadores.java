package compilador.semantico.leitores;

import compilador.Identificador.Identificador;
import compilador.semantico.ListaDeIdentificadores;
import compilador.token.Token;

import java.util.Stack;

public abstract class LeitorDeIdentificadores {
    public abstract void ler(ListaDeIdentificadores listaDeIdentificadores);


}
