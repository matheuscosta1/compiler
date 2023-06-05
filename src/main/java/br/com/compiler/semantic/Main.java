package br.com.compiler.semantic;


import br.com.compiler.lexical.LexicalAnalyzer;
import br.com.compiler.lexical.domain.Characters;
import br.com.compiler.syntactic.SyntacticAnalyzer;
import br.com.compiler.syntactic.domain.ProductionTable;
import br.com.compiler.syntactic.domain.SyntacticTable;
import br.com.compiler.syntactic.domain.Tree;
import br.com.compiler.syntactic.utils.FileHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String codeFileName = "lexical/codigo2.txt";
        String productionsTableFileName = "syntactic/productions.txt";
        String syntacticTableFileName = "syntactic/syntacticTable.txt";

        Characters characters = br.com.compiler.lexical.utils.FileHandler.readFile(codeFileName);

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(characters);

        SyntacticTable syntacticTable = FileHandler.readSyntacticTable(syntacticTableFileName);

        ProductionTable productionTable = FileHandler.readProductions(productionsTableFileName);

        SyntacticAnalyzer syntacticAnalyzer = new SyntacticAnalyzer();

        boolean syntacticAnalyserResult = syntacticAnalyzer.process(syntacticTable, productionTable, lexicalAnalyzer);
        
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();

        if(syntacticAnalyserResult) {
            System.out.println("Programa está sintaticamente correto!\n");

            System.out.println("Árvore Sintática gerada:\n");

            Tree tree = syntacticAnalyzer.getTree();

            tree.printTree("S");

            semanticAnalyzer.process(lexicalAnalyzer, syntacticAnalyzer);

        } else {
            System.out.println("\nPrograma está sintaticamente incorreto!");
        }
    }

}