package br.com.compiler.lexical;

import br.com.compiler.lexical.domain.Characters;
import br.com.compiler.lexical.domain.FileCharacter;
import br.com.compiler.lexical.domain.SymbolTable;
import br.com.compiler.lexical.domain.Token;
import br.com.compiler.lexical.utils.Constants;

public class LexicalAnalyzer {

    Constants constants = new Constants();
    SymbolTable symbolTable = new SymbolTable();
    Characters characters = new Characters();

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public LexicalAnalyzer() {
    }

    public LexicalAnalyzer(Characters characters) {
        this.characters = characters;
    }

    public Token getTokenMock() {
        FileCharacter fileCharacter;

        while (true) {
            fileCharacter = characters.getNextCharacter();
            String symbol = "";

            if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                symbol = fileCharacter.getCharacter();
                String position = symbolTable.addTable(symbol+" ");
                return new Token("ID", position, fileCharacter.getLine(), fileCharacter.getColumn());
            } else if (fileCharacter.getCharacter().equals("$")) { //TODO: Remover esse EOF no final de tudo
                System.out.println(symbolTable);
                return new Token("$", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
            } else {
                return new Token();
            }
        }
    }

    public Token getToken() {

        FileCharacter fileCharacter = null;

        int state = 0;

        String symbol = "";

        while (true) {
            switch (state) {
                case 0 -> {
                    fileCharacter = characters.getNextCharacter();

                    if (fileCharacter.isEqualsToCharacter("=")) {
                        state = 1;
                    } else if (fileCharacter.isEqualsToCharacter(":")) {
                        state = 3;
                    } else if (fileCharacter.isEqualsToCharacter("^")) {
                        state = 4;
                    } else if (fileCharacter.isEqualsToCharacter("/")) {
                        state = 5;
                    } else if (fileCharacter.isEqualsToCharacter("*")) {
                        state = 6;
                    } else if (fileCharacter.isEqualsToCharacter("-")) {
                        state = 7;
                    } else if (fileCharacter.isEqualsToCharacter("+")) {
                        state = 8;
                    } else if (fileCharacter.isEqualsToCharacter(";")) {
                        state = 9;
                    } else if (fileCharacter.isEqualsToCharacter("(")) {
                        state = 10;
                    } else if (fileCharacter.isEqualsToCharacter(")")) {
                        state = 11;
                    } else if (fileCharacter.isEqualsToCharacter(",")) {
                        state = 23;
                    } else if (fileCharacter.isEqualsToCharacter("{")) {
                        state = 12;
                    } else if (fileCharacter.isEqualsToCharacter("}")) {
                        state = 13;
                    } else if (fileCharacter.isEqualsToCharacter("\n")) {
                         return new Token();
                    } else if (fileCharacter.isEqualsToCharacter(" ") || fileCharacter.isEqualsToCharacter("\\")) { //TODO: ficou faltando esse cara no diagrama
                        state = 14;
                    } else if (fileCharacter.isEqualsToCharacter("<")) {
                        state = 16;
                    } else if (fileCharacter.isEqualsToCharacter(">")) {
                        state = 17;
                    } else if (constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 27;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("f")) {
                        state = 36;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("s")) {
                        state = 44;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("e")) {
                        state = 46;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("i")) {
                        state = 51;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("c")) {
                        state = 54;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("r")) {
                        state = 71;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("a")) {
                        state = 77;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("'")) {
                        state = 83;
                    } else if (fileCharacter.getCharacter().equals("$")) { //TODO: Remover esse EOF no final de tudo
                        System.out.println(symbolTable);
                        return new Token("$", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                    } else if (constants.isCharacterNotId(fileCharacter.getCharacter())){
                        state = 25;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        return new Token("ERRO", "ERRO", fileCharacter.getLine(), fileCharacter.getColumn());
                    }
                }

                case 1 -> {
                    fileCharacter = characters.getNextCharacter();
                    if (fileCharacter.isEqualsToCharacter("=")) { // Validação se é token do tipo ==
                        state = 2;
                    } else {
                        state = 15; //Numero de estado referente a atribuicao =
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
                    fileCharacter = characters.getNextCharacter();
                    if(fileCharacter.isEqualsToCharacter("*")) {
                        state = 86;
                        break;
                    }

                    state = 102;
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
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("n") || fileCharacter.isEqualsToCharacter("t") || fileCharacter.isEqualsToCharacter("\\") || fileCharacter.isEqualsToCharacter(" ")) { // ignora /t /n
                        state = 14;
                        break;
                    }

                    state = 101;
                }
                case 15 -> {
                    characters.setLookAhead(true);
                    return new Token("=", "ATR", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 16 -> {
                    fileCharacter = characters.getNextCharacter();
                    if (fileCharacter.isEqualsToCharacter("=")) {
                        state = 18; // <= (less or equals)
                    } else if (fileCharacter.isEqualsToCharacter(">")) {
                        state = 19; // <> (not equals)
                    } else {
                        state = 20; // < (less than)
                    }
                }
                case 17 -> {
                    fileCharacter = characters.getNextCharacter();
                    if (fileCharacter.isEqualsToCharacter("=")) {
                        state = 21; // >=
                    } else {
                        state = 22; // >
                    }
                }
                case 18 -> {
                    return new Token("RELOP", "LE", fileCharacter.getLine(), fileCharacter.getColumn()); // <= (less or equals)
                }
                case 19 -> {
                    return new Token("RELOP", "NE", fileCharacter.getLine(), fileCharacter.getColumn()); // <> (not equals)
                }
                case 20 -> {
                    characters.setLookAhead(true);
                    return new Token("RELOP", "LT", fileCharacter.getLine(), fileCharacter.getColumn()); // < (less than)
                }
                case 21 -> {
                    return new Token("RELOP", "GE", fileCharacter.getLine(), fileCharacter.getColumn()); // >= (greater equals)
                }
                case 22 -> {
                    characters.setLookAhead(true);
                    return new Token("RELOP", "GT", fileCharacter.getLine(), fileCharacter.getColumn()); // > (greater than)
                }
                case 23 -> {
                    return new Token(",", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 25 -> {
                    fileCharacter = characters.getNextCharacter();

                    symbol = symbol.concat(fileCharacter.getCharacter());

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25;
                    } else {
                        state = 104;
                    }
                }
                case 27 -> {  // etapa de reconhecimento de tokens do tipo 1 // 1. // 1E
                    fileCharacter = characters.getNextCharacter();

                    symbol = symbol.concat(fileCharacter.getCharacter());

                    if(constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 27;
                    } else if (fileCharacter.isEqualsToCharacter(".")) {
                        state = 29;
                    } else if (fileCharacter.isEqualsToCharacter("E")) {
                        state = 32;
                    } else if (!constants.isCharacterInNumbers(fileCharacter.getCharacter()) && !fileCharacter.isEqualsToCharacter(".") && !fileCharacter.isEqualsToCharacter("E")) {
                        state = 28;
                    } else {
                        System.out.println(symbolTable);
                        return new Token("ERRO", "ERRO", fileCharacter.getLine(), fileCharacter.getColumn());
                    }
                }
                case 28 -> {
                    String position = symbolTable.addTable(symbol);
                    characters.setLookAhead(true);
                    return new Token("setInt", position, fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 29 -> { // etapa de reconhecimento de tokens do tipo 1.
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 30;
                        break;
                    }
                    System.out.println(symbolTable);
                    return new Token("ERRO", "ERRO", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 30 -> { // etapa de reconhecimento de tokens do tipo 1.32
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 30;
                    } else if (fileCharacter.isEqualsToCharacter("E")) {
                        state = 32;
                    } else {
                        state = 31;
                    }
                }
                case 31 -> {
                    String position = symbolTable.addTable(symbol);
                    characters.setLookAhead(true);
                    return new Token("setFraction", position, fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 32 -> { // etapa de reconhecimento de tokens do tipo 1.32E
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(fileCharacter.isEqualsToCharacter("+") || fileCharacter.isEqualsToCharacter("-")) {
                        state = 33;
                    } else if (constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 34;
                    } else {
                        System.out.println(symbolTable);
                        return new Token("ERRO", "ERRO", fileCharacter.getLine(), fileCharacter.getColumn());
                    }
                }
                case 33 -> { // etapa de reconhecimento de tokens do tipo 1.32E+1
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 34;
                        break;
                    }
                    System.out.println(symbolTable);
                    return new Token("ERRO", "ERRO", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 34 -> { // etapa de reconhecimento de tokens do tipo 1.32E+1
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 34;
                    } else if (!constants.isCharacterInNumbers(fileCharacter.getCharacter())) {
                        state = 35;
                    } else {
                        System.out.println(symbolTable);
                        return new Token("ERRO", "ERRO", fileCharacter.getLine(), fileCharacter.getColumn());
                    }
                }
                case 35 -> {
                    String position = symbolTable.addTable(symbol);
                    characters.setLookAhead(true);
                    return new Token("setFloat", position, fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 36 -> {
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(fileCharacter.isEqualsToCharacter("u")) { // fu
                        state = 37;
                    } else if (fileCharacter.isEqualsToCharacter("l")) { // fl
                        state = 58;
                    } else if (fileCharacter.isEqualsToCharacter("a")) { // fa
                        state = 68;
                    } else {
                        state = 25; //id
                    }
                }
                case 37 -> {
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(fileCharacter.isEqualsToCharacter("n")) { // fun
                        state = 38;
                    } else {
                        state = 25; //id
                    }
                }
                case 38 -> {
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(fileCharacter.isEqualsToCharacter("c")) { // func
                        state = 39;
                    } else {
                        state = 25; //id
                    }
                }
                case 39 -> {
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(fileCharacter.isEqualsToCharacter("t")) { // funct
                        state = 40;
                    } else {
                        state = 25; //id
                    }
                }
                case 40 -> {
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(fileCharacter.isEqualsToCharacter("i")) { // functi
                        state = 41;
                    } else {
                        state = 25; //id
                    }
                }
                case 41 -> {
                    fileCharacter = characters.getNextCharacter();
                    symbol = symbol.concat(fileCharacter.getCharacter());
                    if(fileCharacter.isEqualsToCharacter("o")) { // functio
                        state = 42;
                    } else {
                        state = 25; //id
                    }
                }
                case 42 -> {
                    fileCharacter = characters.getNextCharacter();
                    if(fileCharacter.isEqualsToCharacter("n")) { // function
                        state = 43;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25; //id
                    }
                }
                case 43 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 90;
                }
                case 44 -> {
                    fileCharacter = characters.getNextCharacter();
                    if(fileCharacter.isEqualsToCharacter("e")) { // se
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 45;
                    } else {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25; //id
                    }
                }
                case 45 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("n")) { //sen
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 80;
                        break;
                    } else if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 93;
                }
                case 46-> {
                    fileCharacter = characters.getNextCharacter();
                    if(fileCharacter.isEqualsToCharacter("n")) { //en
                        state = 47;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 46;
                    }
                }
                case 47-> {
                    fileCharacter = characters.getNextCharacter();
                    if(fileCharacter.isEqualsToCharacter("t")) { // ent
                        state = 48;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (fileCharacter.isEqualsToCharacter("q")) { // enq
                        state = 62;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 47;
                    }
                }
                case 48-> {
                    fileCharacter = characters.getNextCharacter();
                    if(fileCharacter.isEqualsToCharacter("a")) { //enta
                        state = 49;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 48;
                    }
                }
                case 49-> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("o")) { //entao
                        state = 50;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 49;
                    }
                }
                case 50 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) { //valida se é um id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 95;
                }
                case 51 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("n")) { //in
                        state = 52;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 51;
                    }
                }
                case 52 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("t")) { //int
                        state = 53;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 52;
                    }
                }
                case 53 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 97;
                }
                case 54 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("h")) { //ch
                        state = 55;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 54;
                    }
                }
                case 55 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("a")) { //cha
                        state = 56;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 55;
                    }
                }
                case 56 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("r")) { //char
                        state = 57;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 56;
                    }
                }
                case 57 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 98;
                }
                case 58 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("o")) { // flo
                        state = 59;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 58;
                    }
                }
                case 59 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("a")) { // floa
                        state = 60;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 59;
                    }
                }
                case 60 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("t")) { // float
                        state = 61;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 60;
                    }
                }
                case 61 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 91;
                }
                case 62 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("u")) { // enqu
                        state = 63;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 62;
                    }
                }
                case 63 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("a")) { // enqua
                        state = 64;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 63;
                    }
                }
                case 64 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("n")) { // enquan
                        state = 65;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 64;
                    }
                }
                case 65 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("t")) { // enquant
                        state = 66;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 65;
                    }
                }
                case 66 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("o")) { // enquanto
                        state = 67;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 65;
                    }
                }
                case 67 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 96;
                }
                case 68 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("c")) { // fac
                        state = 69;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 68;
                    }
                }
                case 69 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("a")) { // faca
                        state = 70;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 69;
                    }
                }
                case 70 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }
                    state = 92;
                }
                case 71 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("e")) { // re
                        state = 72;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 71;
                    }
                }
                case 72 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("p")) { // rep
                        state = 73;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 72;
                    }
                }
                case 73 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("i")) { // repi
                        state = 74;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 73;
                    }
                }
                case 74 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("t")) { // repit
                        state = 75;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 74;
                    }
                }
                case 75 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("a")) { // repita
                        state = 76;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 75;
                    }
                }
                case 76 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 99;
                }
                case 77 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("t")) { // at
                        state = 78;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 25;
                    }
                }
                case 78 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("e")) { // ate
                        state = 79;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 78;
                    }
                }
                case 79 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 100;
                }
                case 80 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("a")) { // sena
                        state = 81;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 80;
                    }
                }
                case 81 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("o")) { // senao
                        state = 82;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else if (constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        state = 25; //id
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        state = 81;
                    }
                }
                case 82 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(constants.isCharacterInIDS(fileCharacter.getCharacter())) {
                        symbol = symbol.concat(fileCharacter.getCharacter());
                        state = 25;
                        break;
                    }

                    state = 94;
                }
                case 83 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(!fileCharacter.isEqualsToCharacter("'")) { //
                        state = 84;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    }
                }
                case 84 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("'")) {
                        state = 85;
                        symbol = symbol.concat(fileCharacter.getCharacter());
                    } else {
                        return new Token("ERRO", "ERRO", fileCharacter.getLine(), fileCharacter.getColumn());
                    }
                }
                case 85 -> {
                    fileCharacter = characters.getNextCharacter();
                    state = 103;
                }
                case 86 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(!fileCharacter.isEqualsToCharacter("*")) {
                        state = 86;
                        break;
                    }

                    state = 87;
                }
                case 87 -> {
                    fileCharacter = characters.getNextCharacter();

                    if(fileCharacter.isEqualsToCharacter("/")) {
                        state = 88;
                    }
                }
                case 88 -> {
                    fileCharacter = characters.getNextCharacter();
                    if(!fileCharacter.isEqualsToCharacter("/")) {
                        state = 89;
                    }
                }
                case 89 -> {
                    return new Token();
                }
                case 90 -> {
                    characters.setLookAhead(true);
                    return new Token("function", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 91 -> {
                    characters.setLookAhead(true);
                    return new Token("float", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 92 -> {
                    characters.setLookAhead(true);
                    return new Token("faca", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 93 -> {
                    characters.setLookAhead(true);
                    return new Token("se", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 94 -> {
                    characters.setLookAhead(true);
                    return new Token("senao", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 95 -> {
                    characters.setLookAhead(true);
                    return new Token("entao", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 96 -> {
                    characters.setLookAhead(true);
                    return new Token("enquanto", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 97 -> {
                    characters.setLookAhead(true);
                    return new Token("int", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 98 -> {
                    characters.setLookAhead(true);
                    return new Token("char", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 99 -> {
                    characters.setLookAhead(true);
                    return new Token("repita", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 100 -> {
                    characters.setLookAhead(true);
                    return new Token("ate", "NULL", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 101 -> {
                    characters.setLookAhead(true);
                    return new Token();
                }
                case 102 -> {
                    characters.setLookAhead(true);
                    return new Token("/", "DIV", fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 103 -> {
                    String position = symbolTable.addTable(symbol);
                    characters.setLookAhead(true);
                    return new Token("caracter", position, fileCharacter.getLine(), fileCharacter.getColumn());
                }
                case 104 -> {
                    String position = symbolTable.addTable(symbol);
                    characters.setLookAhead(true);
                    return new Token("ID", position, fileCharacter.getLine(), fileCharacter.getColumn());
                }
            }
        }
    }
}
