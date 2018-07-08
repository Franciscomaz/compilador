package compilador.semantico.Escopo;

import compilador.Identificador.Identificador;
import compilador.Identificador.Program;
import compilador.semantico.ErroSemantico;
import compilador.token.Token;

public class EscopoGlobal extends Escopo {
    private final Program program;

    public EscopoGlobal(Program program) {
        this.program = program;
    }

    public Identificador buscar(Token token) throws ErroSemantico {
        if(!variaveis.contem(token.palavra())){
            throw new ErroSemantico("identificador não declarado", token);
        }
        return variaveis.buscar(token);
    }
}
