package br.com.compiler;

import br.com.compiler.domain.Characters;
import br.com.compiler.domain.FileCharacter;
import br.com.compiler.domain.SymbolTable;
import br.com.compiler.domain.Token;
import br.com.compiler.utils.Constants;

import java.util.List;

public class LexicalAnalyzer {

    Constants constants = new Constants();
    SymbolTable symbolTable = new SymbolTable();
    public LexicalAnalyzer() {
    }

    public Token findToken(Characters characters) {

        FileCharacter fileCharacter = null;

        int state = 0;

        //characters.setLookAhead(false);

        while (true) {
            switch (state) {
                case 0 -> {
                    fileCharacter = characters.getNextCharacter();
                    if (fileCharacter.isEqualsToCharacter("=")) {
                        state = 1;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter(":")) {
                        state = 3;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("^")) {
                        state = 4;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("/")) {
                        state = 5;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("*")) {
                        state = 6;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("-")) {
                        state = 7;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("+")) {
                        state = 8;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter(";")) {
                        state = 9;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("(")) {
                        state = 10;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter(")")) {
                        state = 11;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("{")) {
                        state = 12;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("}")) {
                        state = 13;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter(" ") || fileCharacter.isEqualsToCharacter("\n") || fileCharacter.isEqualsToCharacter("\t")) { //TODO: ficou faltando esse cara no diagrama
                        state = 14;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter("<")) {
                        state = 16;
                        break;
                    } else if (fileCharacter.isEqualsToCharacter(">")) {
                        state = 17;
                        break;
                    }
                    if (fileCharacter.getCharacter().equals("EOF")) { //TODO: Remover esse EOF no final de tudo
                        return new Token("EOF", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                    }
                }
                case 1 -> {
                    fileCharacter = characters.getNextCharacter();
                    if (fileCharacter.isEqualsToCharacter("=")) {
                        state = 2;
                    } else {
                        state = 15; //Numero de estado referente a atribuicao =
                        characters.setLookAhead(true);
                    }
                }
                case 2 -> {
                    return new Token("RELOP", "EQ", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 3 -> {
                    return new Token(":", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 4 -> {
                    return new Token("^", "EXP", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 5 -> {
                    return new Token("/", "DIV", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 6 -> {
                    return new Token("*", "MUL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 7 -> {
                    return new Token("-", "SUB", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 8 -> {
                    return new Token("+", "ADD", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 9 -> {
                    return new Token(";", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 10 -> {
                    return new Token("(", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 11 -> {
                    return new Token(")", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 12 -> {
                    return new Token("{", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 13 -> {
                    return new Token("}", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 14 -> {
                    return new Token();
                }
                case 15 -> {
                    return new Token("=", "ATR", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 16 -> {
                    fileCharacter = characters.getNextCharacter();
                    if (fileCharacter.isEqualsToCharacter("=")) {
                        state = 18; // <= (less or equals)
                    } else if (fileCharacter.isEqualsToCharacter(">")) {
                        state = 19; // <> (not equals)
                    } else {
                        characters.setLookAhead(true); // < (less than)
                        state = 20;
                    }
                }
                case 17 -> {
                    fileCharacter = characters.getNextCharacter();
                    if (fileCharacter.isEqualsToCharacter("=")) {
                        state = 21; // >=
                    } else {
                        state = 22; // >
                        characters.setLookAhead(true);
                    }
                }
                case 18 -> {
                    return new Token("RELOP", "LE", fileCharacter.getLine(), fileCharacter.getColumn()); // <= (less or equals)
                }
                case 19 -> {
                    return new Token("RELOP", "NE", fileCharacter.getLine(), fileCharacter.getColumn()); // <> (not equals)
                }
                case 20 -> {
                    return new Token("RELOP", "LT", fileCharacter.getLine(), fileCharacter.getColumn()); // < (less than)
                }
                case 21 -> {
                    return new Token("RELOP", "GE", fileCharacter.getLine(), fileCharacter.getColumn()); // >= (greater equals)
                }
                case 22 -> {
                    return new Token("RELOP", "GT", fileCharacter.getLine(), fileCharacter.getColumn()); // > (greater than)
                }
            }
        }
    }
}
