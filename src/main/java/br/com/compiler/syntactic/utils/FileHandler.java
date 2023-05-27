package br.com.compiler.syntactic.utils;

import br.com.compiler.syntactic.domain.Production;
import br.com.compiler.syntactic.domain.ProductionTable;
import br.com.compiler.syntactic.domain.Symbol;
import br.com.compiler.syntactic.domain.SyntacticTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FileHandler {
    public static ProductionTable readProductions(String fileName) throws IOException {
        InputStream inputStream = Objects.requireNonNull(FileHandler.class.getClassLoader().getResource(fileName)).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        reader.readLine(); // lê primeira linha e a ignora (cabeçalho)

        String line;

        ProductionTable productionTable = new ProductionTable();

        while ((line = reader.readLine()) != null) {
            Production productions = new Production();

            String[] columns = line.split(",");

            Integer productionPosition = Integer.valueOf(columns[0]);

            String[] productionSymbols = columns[1].split("->");

            String productionHead = productionSymbols[0].trim();

            String[] productionBody = productionSymbols[1].trim().split(" ");

            for (String production : productionBody) {
                boolean isEmpty = production.equals("_");
                Symbol symbol = new Symbol(productionHead, production, !isEmpty && (Character.isLowerCase(production.charAt(0)) || !Character.isLetterOrDigit(production.charAt(0))), isEmpty);
                productions.append(symbol);
            }

            Collections.reverse(productions.getProductions()); //inverte produções para deixar preparado pra quando inserir na pilha inserir corretamente

            productionTable.addTable(productionPosition, productions);
        }

        return productionTable;
    }

    public static SyntacticTable readSyntacticTable(String fileName) throws IOException {
        InputStream inputStream = Objects.requireNonNull(FileHandler.class.getClassLoader().getResource(fileName)).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        SyntacticTable syntacticTable = new SyntacticTable();

        String line;

        line = reader.readLine(); // lê primeira linha e a ignora

        String[] firstRow = line.split(",");

        String[] terminalSymbolsWithoutSymbolState = new String[firstRow.length - 1];
        System.arraycopy(firstRow, 1, terminalSymbolsWithoutSymbolState, 0, terminalSymbolsWithoutSymbolState.length); //remove symbol state

        HashMap<String, HashMap<String, Integer>> table = new HashMap<>();


        while ((line = reader.readLine()) != null) {
            HashMap<String, Integer> hashMap = new HashMap<>();

            String[] nonTerminalSymbols = line.split(",");

            String head = Arrays.asList(nonTerminalSymbols).get(0);
            table.put(head, new HashMap<>());

            String[] nonTerminalSymbolsWithoutHead = new String[nonTerminalSymbols.length - 1];
            System.arraycopy(nonTerminalSymbols, 1, nonTerminalSymbolsWithoutHead, 0, nonTerminalSymbolsWithoutHead.length); //remove head non terminal symbol. Ex.: S. Sobra apenas as colunas referentes às posicoes na tabela de producoes

            for (int iterator = 0; iterator < terminalSymbolsWithoutSymbolState.length; iterator++) {
                String nonTerminalSymbol = Arrays.asList(nonTerminalSymbolsWithoutHead).get(iterator);
                String terminalSymbol = Arrays.asList(terminalSymbolsWithoutSymbolState).get(iterator);
                hashMap.put(terminalSymbol, toIntOrNull(nonTerminalSymbol));
                table.put(head, hashMap);
            } //Mapeia cada linha de símbolos Não terminais com cada coluna de símbolos terminais, gerando um HashMap no formato: A -> (a -> null, b -> 5, c -> null, d -> 6, $ -> null)

        }
        syntacticTable.setTable(table);
        return syntacticTable;
    }

    public static Integer toIntOrNull(String string) {
        Integer i = null;
        try {
            i = Integer.valueOf(string);
        } catch (NumberFormatException e) {
            //ignore
        }
        return i;
    }
}
