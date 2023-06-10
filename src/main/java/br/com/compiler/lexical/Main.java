package br.com.compiler.lexical;

import br.com.compiler.lexical.domain.Characters;
import br.com.compiler.lexical.domain.Token;
import br.com.compiler.lexical.utils.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "lexical/codigo2.txt";

        Characters characters = FileHandler.readFile(fileName);

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(characters);

        List<Token> tokens = new ArrayList<>();

        while (true) {
            Token token = lexicalAnalyzer.getToken();

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

            if(token.getName().equals("$")) {
                break;
            }

            System.out.println(token);

            tokens.add(token);

        }
        System.out.println("Tokens: \n");
        System.out.println(tokens);
    }

}