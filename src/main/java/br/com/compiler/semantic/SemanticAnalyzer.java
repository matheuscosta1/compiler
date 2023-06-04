package br.com.compiler.semantic;

import br.com.compiler.lexical.LexicalAnalyzer;
import br.com.compiler.lexical.domain.SymbolTable;
import br.com.compiler.syntactic.SyntacticAnalyzer;
import br.com.compiler.syntactic.domain.Tree;

import java.util.List;

public class SemanticAnalyzer {

    SymbolTable symbolTable = new SymbolTable();

    public void analyze(Tree root) {
        analyzeFunction(root);
    }

    private void analyzeFunction(Tree node) {
        String nodeName = node.getName();

        if (nodeName.equals("nome_programa")) {
            // Faça algo com o nome do programa

            Tree blockNode = node.getChildren().get(0);
            analyzeBlock(blockNode);
        }
    }

    private void analyzeBlock(Tree node) {
        String nodeName = node.getName();

        if (nodeName.equals("declaracoes_variaveis")) {
            // Realize a análise semântica das declarações de variáveis

            List<Tree> children = node.getChildren();
            for (Tree child : children) {
                analyzeDeclaration(child);
            }
        } else if (nodeName.equals("sequencia_comandos")) {
            // Realize a análise semântica da sequência de comandos

            List<Tree> children = node.getChildren();
            for (Tree child : children) {
                analyzeCommand(child);
            }
        }
    }

    private void analyzeDeclaration(Tree node) {
        String nodeName = node.getName();

        if (nodeName.equals("tipo")) {
            // Obtenha o tipo da declaração
            String type = node.getAttribute();

            Tree idListNode = node.getChildren().get(0);
            analyzeIdList(idListNode, type);
        } else if (nodeName.equals("declaracoes_variaveis")) {
            Tree declarationsNode = node.getChildren().get(0);
            analyzeDeclaration(declarationsNode);
        }
    }

    private void analyzeIdList(Tree node, String type) {
        String nodeName = node.getName();

        if (nodeName.equals("identificador")) {
            // Obtenha o nome do identificador
            String identifier = node.getAttribute();

            // Verifique se o identificador já foi declarado

            if (symbolTable.isSymbolOnTable(identifier)) {
                System.out.println("Erro: identificador já declarado");
            } else {
                // Adicione o identificador na tabela de símbolos com o tipo correspondente
                br.com.compiler.lexical.domain.Symbol symbol = symbolTable.getSymbol(identifier);
                symbol.setDataType(type);

                //symbolTable.put(identifier, type);
            }
        } else if (nodeName.equals("lista_ids")) {
            Tree idNode = node.getChildren().get(0);
            analyzeIdList(idNode, type);

            Tree idListRestNode = node.getChildren().get(1);
            analyzeIdList(idListRestNode, type);
        }
    }

    private void analyzeCommand(Tree node) {
        String nodeName = node.getName();

        if (nodeName.equals("selecao")) {
            // Realize a análise semântica da estrutura de seleção
            // Implemente a lógica de acordo com a estrutura específica

            Tree conditionNode = node.getChildren().get(0);
            Tree trueBranchNode = node.getChildren().get(1);
            Tree falseBranchNode = node.getChildren().get(2);

            analyzeCondition(conditionNode);
            analyzeBlock(trueBranchNode);
            analyzeBlock(falseBranchNode);
        } else if (nodeName.equals("laco_repita")) {
            // Realize a análise semântica do laço de repetição "repita"
            // Implemente a lógica de acordo com a estrutura específica

            Tree structureNode = node.getChildren().get(0);
            Tree conditionNode = node.getChildren().get(1);

            analyzeBlock(structureNode);
            analyzeCondition(conditionNode);
        } else if (nodeName.equals("laco_enquanto")) {
            // Realize a análise semântica do laço de repetição "enquanto"
            // Implemente a lógica de acordo com a estrutura específica

            Tree conditionNode = node.getChildren().get(0);
            Tree structureNode = node.getChildren().get(1);
            analyzeCondition(conditionNode);
            analyzeBlock(structureNode);
        } else if (nodeName.equals("atribuicao")) {
            // Realize a análise semântica da atribuição
            // Implemente a lógica de acordo com a estrutura específica

            String identifier = node.getChildren().get(0).getAttribute();
            String expression = node.getChildren().get(1).getAttribute();

            // Verifique se o identificador foi declarado
            if (!symbolTable.isSymbolOnTable(identifier)) {
                System.out.println("Erro: identificador não declarado");
            } else {
                // Faça algo com a atribuição semântica
            }
        }
    }

    private void analyzeCondition(Tree tree) {
        String nodeName = tree.getName();

        if (nodeName.equals("condicao")) {
            // Realize a análise semântica da condição
            // Implemente a lógica de acordo com a estrutura específica

            Tree expression1Node = tree.getChildren().get(0);
            Tree relationalOperatorNode = tree.getChildren().get(1);
            Tree expression2Node = tree.getChildren().get(2);

            String expression1 = expression1Node.getAttribute();
            String operator = relationalOperatorNode.getAttribute();
            String expression2 = expression2Node.getAttribute();

            // Faça algo com a condição semântica
        }
    }


    public void process(LexicalAnalyzer lexicalAnalyzer, SyntacticAnalyzer syntacticAnalyzer) {

        symbolTable = lexicalAnalyzer.getSymbolTable();

        Tree tree = syntacticAnalyzer.getTree();

        Tree root = tree.getRoot("root");

        analyze(root);

        System.out.println(symbolTable);
    }
}
