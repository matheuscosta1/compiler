package br.com.compiler.syntactic;

import br.com.compiler.lexical.LexicalAnalyzer;
import br.com.compiler.lexical.domain.Token;
import br.com.compiler.syntactic.domain.*;

import java.util.*;

public class SyntacticAnalyzer {

    private static final Deque<Symbol> stack = new ArrayDeque<>();

    public Tree tree = new Tree();


    public Tree getTree() {
        return tree;
    }

    public boolean process(SyntacticTable syntacticTable, ProductionTable productionTable, LexicalAnalyzer lexicalAnalyzer) {
        List<Symbol> productions1 = productionTable.getProductions(1);

        Symbol symbol1 = productions1.stream().findFirst().get();

        stack.addLast(symbol1);

        tree = new Tree("root");

        Token token = lexicalAnalyzer.getTokenMock();
        Symbol symbol;
        while (!stack.isEmpty()) {
            symbol = stack.getLast();

            String productionTokenName = "";

            if(token.getName() != null) {
                if(!token.getAttribute().equals("-")) {
                    productionTokenName = lexicalAnalyzer.getSymbolTable().getSymbol(token.getAttribute()); //consulta tabela de simbolos para pegar nome do atributo do token
                }
            }

            System.out.println("---------------------------------------------------------\n");

            System.out.println("Pilha: \n");

            stack.forEach(System.out::println);

            System.out.println("\nTopo pilha: \n");

            System.out.println(symbol);

            System.out.println("\nToken a reconhecer: " + productionTokenName);

            if(symbol.isTerminal()) {
                if(symbol.getDerivation().equals(productionTokenName)) {
                    Symbol dequeueSymbol = stack.removeLast();

                    System.out.println("\nSimbolo desempilhado: \n");
                    System.out.println(dequeueSymbol);
                    System.out.println("---------------------------------------------------------\n");

                    token = lexicalAnalyzer.getTokenMock();
                } else {
                    System.out.println("Valor esperado: " + symbol.getDerivation());
                    return false;
                }
            } else if (symbol.isEmpty()) {

                symbol = stack.removeLast();

                System.out.println("\nSimbolo desempilhado: \n");
                System.out.println(symbol);
                System.out.println("---------------------------------------------------------\n");

            } else if (token.getName() == null) {
                token = lexicalAnalyzer.getTokenMock();
            } else {
                String symbolDerivation = symbol.getDerivation(); //symbolDerivation indica os não terminais na tabela
                Integer productionDerivation = syntacticTable.getProductionDerivation(symbolDerivation, productionTokenName); //productionTokenName indica a coluna dos terminais na tabela
                if (productionDerivation == null) {
                    System.out.println("Erro: derivação [" + symbol.getDerivation() + "]["+ productionTokenName +"] - " + null);
                    break;
                } else {
                    //trata producao
                    List<Symbol> productions = productionTable.getProductions(productionDerivation);

                    for (Symbol production : productions) {
                        tree.addChild(production.getHead(), production.getDerivation());
                    }

                    symbol = stack.removeLast();

                    System.out.println("\nSimbolo desempilhado: \n");
                    System.out.println(symbol);
                    System.out.println("---------------------------------------------------------\n");

                    productions.forEach(stack::addLast);
                }
            }
        }

        token = lexicalAnalyzer.getTokenMock();

        return Objects.equals(token.getName(), "$");
    }
}
