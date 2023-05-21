package br.com.compiler;

import br.com.compiler.domain.Characters;
import br.com.compiler.domain.Token;
import br.com.compiler.utils.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "codigo.txt";

        Characters characters = FileHandler.readFile(fileName);

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

        List<Token> tokens = new ArrayList<>();


        while (true) {
            Token token = lexicalAnalyzer.findToken(characters);

            if(token.getName() == null) {
                continue;
            }

            if(token.getName().equals("ERRO")) {
                System.out.printf("\nErro encontrado na linha %s e coluna %s\n\n", token.getLine(), token.getColumn());
                break;
            }

            if(token.getAttribute().equals("NULL")) {
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