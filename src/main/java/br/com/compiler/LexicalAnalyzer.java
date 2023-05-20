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

        while (true) {
            switch (state) {
                case 0:
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.getCharacter().equals(":")) {
                        state = 1;
                        break;
                    }

                    if(fileCharacter.getCharacter().equals("EOF")) { //TODO: Remover esse EOF no final de tudo
                        return new Token("EOF", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                    }

                case 1:
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.getCharacter().equals("=")) {
                        state = 2;
                    } else {
                        state = 3;
                        characters.setLookAhead(true);
                    }
                    break;
                case 2:
                    return new Token(":=", "ATR", fileCharacter.getLine(), fileCharacter.getColumn());
                case 3:
                    return new Token(":", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                case 4:
                    return new Token();

            }
        }
    }
}
