package br.com.compiler;

import br.com.compiler.domain.Characters;
import br.com.compiler.domain.SymbolTable;
import br.com.compiler.domain.Token;
import br.com.compiler.utils.Constants;
import br.com.compiler.utils.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "codigo.txt";

        Characters characters = FileHandler.readFile(fileName);

        SymbolTable symbolTable = new SymbolTable();

        SymbolTable.Symbol symbol = new SymbolTable.Symbol("setExp", "12E+1");
        SymbolTable.Symbol symbol1 = new SymbolTable.Symbol("setExp", "1.32E-12");
        SymbolTable.Symbol symbol2 = new SymbolTable.Symbol("ID", "programa");

        symbolTable.addTable(symbol);
        symbolTable.addTable(symbol1);
        symbolTable.addTable(symbol2);

        System.out.println(symbolTable);

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

        List<Token> tokens = new ArrayList<>();


        while (true) {
            Token token = lexicalAnalyzer.findToken(characters);

            if(token.getName() == null) {
                continue;
            }

            if(token.getName().equals("ERRO")) {
                System.out.printf("Erro encontrado na linha %s e coluna %s", token.getLine(), token.getColumn());
                break;
            }

            if(token.getName().equals("NULL")) {
                token.setAttribute("-");
            }

            if(token.getName().equals("EOF")) {
                break;
            }

            System.out.println(token);

            tokens.add(token);

        }
        System.out.println(tokens);
    }

}