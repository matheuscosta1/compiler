package br.com.compiler.syntactic;


import br.com.compiler.lexical.LexicalAnalyzer;
import br.com.compiler.lexical.domain.Characters;
import br.com.compiler.syntactic.domain.ProductionTable;
import br.com.compiler.syntactic.domain.SyntacticTable;
import br.com.compiler.syntactic.utils.FileHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String codeFileName = "lexical/codigo3.txt";
        String productionsTableFileName = "syntactic/productions.txt";
        String syntacticTableFileName = "syntactic/syntacticTable.txt";

        Characters characters = br.com.compiler.lexical.utils.FileHandler.readFile(codeFileName);

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(characters);

        SyntacticTable syntacticTable = FileHandler.readSyntacticTable(syntacticTableFileName);

        ProductionTable productionTable = FileHandler.readProductions(productionsTableFileName);

        SyntacticAnalyzer syntacticAnalyzer = new SyntacticAnalyzer();

        boolean result = syntacticAnalyzer.process(syntacticTable, productionTable, lexicalAnalyzer);

        if(result) {
            System.out.println("Programa foi aceito!\n");
        } else {
            System.out.println("\nPrograma rejeitado!");
        }

        System.out.println("Árvore Sintática gerada:\n");
        syntacticAnalyzer.getTree().printTree("S");
    }

}