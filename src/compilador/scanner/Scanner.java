package compilador.scanner;

import compilador.token.Token;
import compilador.token.TokenFactory;
import java.util.Stack;

public class Scanner {

    private final Leitor leitor;
    private final Stack<Token> bufferTokens;

    public Scanner(Leitor leitor) {
        this.leitor = leitor;
        this.bufferTokens = new Stack<>();
    }

    public Stack<Token> geTokens() throws ErroLexico {
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            if (Character.isWhitespace(caracter)) {
                continue;
            }
            if (Character.isLetter(caracter) || caracter == '_') {
                leitor.rollBack();
                bufferTokens.add(lerIdentificador());
            } else if (Character.isDigit(caracter)) {
                leitor.rollBack();
                bufferTokens.add(lerDigito());
            } else if (caracter.toString().matches("<|>|=")) {
                leitor.rollBack();
                bufferTokens.add(lerOperadorRelacional());
            } else if (caracter.toString().matches("$|;|,|\\.")) {
                leitor.rollBack();
                bufferTokens.add(lerCaracteresEspeciais());
            } else if (caracter == '\'') {
                bufferTokens.add(lerLiteral());
            } else if (caracter == ':') {
                leitor.rollBack();
                bufferTokens.add(lerAtribuidor());
            } else if (caracter.toString().matches("\\+|\\*|\\-|/")) {
                bufferTokens.add(TokenFactory.criarToken(caracter.toString(), leitor.getPosicao()));
            } else if (caracter.toString().matches("\\(|\\)|\\[|\\]")) {
                leitor.rollBack();
                lerCaracteresDeAberturaOuFechamento();
            } else {
                throw new ErroLexico(leitor.getPosicao(), "Carácter desconhecido '" + caracter + "'.");
            }
        }
        return bufferTokens;
    }

    private Token lerIdentificador() throws ErroLexico {
        String lexema = "";
        while (leitor.hasNext()) {
            if (lexema.length() > 30) {
                throw new ErroLexico(leitor.getPosicao(), "Valor maior do que o permitido.");
            }
            Character caracter = leitor.proximoCaracter();
            if (!Character.isLetterOrDigit(caracter) && caracter != '_') {
                leitor.rollBack();
                break;
            }
            lexema += caracter;
        }
        return TokenFactory.criarIdentificador(lexema, leitor.getPosicao());
    }

    private Token lerDigito() throws ErroLexico {
        String lexema = "";
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            if (Character.isLetter(caracter) || caracter == '_') {
                throw new ErroLexico(leitor.getPosicao(), "Carácter inválido '" + caracter + "'.");
            }
            if (!Character.isDigit(caracter)) {
                leitor.rollBack();
                break;
            }
            lexema += caracter;
            //Trata se o numero for maior que a quantidade permitida...
            int valorInteiro = Integer.parseInt(lexema);
            if (valorInteiro > 32767) {
                throw new ErroLexico(leitor.getPosicao(), "Valor maior do que 32767.");
            } else if (valorInteiro < -32767){
                throw new ErroLexico(leitor.getPosicao(), "Valor menor do que -32767.");
            }
        }
        return TokenFactory.criarInteiro(lexema, leitor.getPosicao());
    }

    private Token lerOperadorRelacional() {
        Character caracter = leitor.proximoCaracter();
        String lexema = caracter.toString();
        if (caracter == '>' && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (caracter == '=') {
                lexema += caracter;
            } else {
                leitor.rollBack();
            }
        } else if (caracter == '<' && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            switch (caracter) {
                case '=':
                    lexema += caracter;
                    break;
                case '>':
                    lexema += caracter;
                    break;
                default:
                    leitor.rollBack();
                    break;
            }
        }
        return TokenFactory.criarToken(lexema, leitor.getPosicao());
    }

    private Token lerCaracteresEspeciais() {
        Character caracter = leitor.proximoCaracter();
        String lexema = caracter.toString();
        if (caracter == '.' && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (caracter == '.') {
                lexema += caracter;
            } else {
                leitor.rollBack();
            }
        }
        return TokenFactory.criarToken(lexema, leitor.getPosicao());
    }

    private Token lerLiteral() throws ErroLexico {
        String lexema = "";
        Character caracter = null;
        while (leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (lexema.length() > 255) {
                throw new ErroLexico(leitor.getPosicao(), "Quantidade de carácteres superior ao permitido '255'.");
            }
            if (caracter == '\'') {
                break;
            }
            lexema += caracter;
        }
        if (caracter != '\'') {
            throw new ErroLexico(leitor.getPosicao(), "Esperado carácter de fechamento '\''.");
        }
        return TokenFactory.criarLiteral(lexema, leitor.getPosicao());
    }

    private Token lerAtribuidor() {
        String lexema = leitor.proximoCaracter().toString();
        Character caracter = leitor.proximoCaracter();
        if (caracter != '=') {
            leitor.rollBack();
        } else {
            lexema += caracter;
        }
        return TokenFactory.criarToken(lexema, leitor.getPosicao());
    }

    private void lerCaracteresDeAberturaOuFechamento() throws ErroLexico {
        String lexema = leitor.proximoCaracter().toString();
        if (leitor.proximoCaracter() == '*') {
            lerComentario();
            return;
        }
        bufferTokens.push(TokenFactory.criarToken(lexema, leitor.getPosicao()));
        leitor.rollBack();
    }

    private void lerComentario() throws ErroLexico {
        boolean isFimDoComentario = false;
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            if (caracter == '*') {
                if (leitor.proximoCaracter() == ')') {
                    isFimDoComentario = true;
                    break;
                }
                leitor.rollBack();
            }
        }
        if (!isFimDoComentario) {
            throw new ErroLexico(leitor.getPosicao(), "É necessário encerrar o comentário.");
        }
    }
}
