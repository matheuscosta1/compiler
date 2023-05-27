package br.com.compiler.syntactic;

import br.com.compiler.lexical.LexicalAnalyzer;
import br.com.compiler.lexical.domain.SymbolTable;
import br.com.compiler.lexical.domain.Token;
import br.com.compiler.syntactic.domain.Production;
import br.com.compiler.syntactic.domain.ProductionTable;
import br.com.compiler.syntactic.domain.Symbol;
import br.com.compiler.syntactic.domain.SyntacticTable;

import java.util.*;

public class SyntacticAnalyzer {

    private static final Deque<Symbol> stack = new ArrayDeque<>();

    public boolean process(SyntacticTable syntacticTable, ProductionTable productionTable, LexicalAnalyzer lexicalAnalyzer) {
        List<Symbol> productions1 = productionTable.getProductions(1);
        //Collections.reverse(productions1); //inverte produções para adicionar na pilha na ordem correta
        Symbol symbol1 = productions1.stream().findFirst().get();
        stack.addLast(symbol1);

        //adicionar na arvore

        Token token = lexicalAnalyzer.getTokenMock();
        // avança pro próximo filho

        while (!stack.isEmpty()) {
            Symbol symbol = stack.getLast();

            String productionTokenName = "";

            if(token.getName() != null) {
                if(!token.getAttribute().equals("-")) {
                    productionTokenName = lexicalAnalyzer.getSymbolTable().getSymbol(token.getAttribute()); //consulta tabela de simbolos para pegar nome do atributo
                }
            }
            System.out.println("---------------------------------------------------------\n");
            System.out.println("All Stack: ");
            stack.forEach(System.out::println);
            System.out.println("Topo pilha: ");
            System.out.println(symbol);
            System.out.println("Token a reconhecer: " + productionTokenName);

            System.out.println("---------------------------------------------------------\n");
            if(symbol.isTerminal()) {
                if(symbol.getValue().equals(productionTokenName)) {
                    Symbol dequeueSymbol = stack.removeLast();

                    System.out.println("Simbolo desempilhado: ");
                    System.out.println(dequeueSymbol);

                    token = lexicalAnalyzer.getTokenMock();

                    if(token.getName() == null) {
                        token = lexicalAnalyzer.getTokenMock();
                    }
                    //avança pro próximo filho na árvore
                } else {
                    System.out.println("Valor esperado: " + symbol.getValue());
                    return false;
                }
            } else if (symbol.isEmpty()) {
                Symbol symbol2 = stack.removeLast();
                System.out.println("Simbolo desempilhado: ");
                System.out.println(symbol2);
                //avanca pro próximo filho na árvore
            } else if (token.getName() == null) {
                token = lexicalAnalyzer.getTokenMock();
            } else {
                String symbolDerivation = symbol.getValue(); //symbolDerivation indica os não terminais na tabela
                Integer productionDerivation = syntacticTable.getProductionDerivation(symbolDerivation, productionTokenName); //productionTokenName indica a coluna dos terminais na tabela
                if (productionDerivation == null) {
                    System.out.println("Erro: derivação [" + symbol.getValue() + "]["+ token.getName()+"] - " + null);
                } else {
                    //trata producao
                    Symbol symbol2 = stack.removeLast();
                    System.out.println("Simbolo desempilhado: ");
                    System.out.println(symbol2);

                    List<Symbol> productions = productionTable.getProductions(productionDerivation);

                    productions.forEach(stack::addLast);
                    //productions.forEach(production -> stack.addLast(symbol)); //adiciona cada derivacao na árvore

                    //avanca filho na arvore
                }
            }
        }
        return Objects.equals(token.getName(), "$");
    }

    private static Deque<Symbol> reverseStack(Deque<Symbol> originalStack) {
        Deque<Symbol> reversedStack = new ArrayDeque<>();

        while (!originalStack.isEmpty()) {
            Symbol item = originalStack.pop();
            reversedStack.push(item);
        }

        return reversedStack;
    }
}
