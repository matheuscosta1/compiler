package br.com.compiler.domain;

import java.util.HashMap;

public class SymbolTable { //TODO: na especificacao do trabalho falou sobre prencher tabela de simbolos com tipo do token, lexema, valor e tipo do dado
    private final HashMap<String, String> table = new HashMap<>();
    private Integer position;

    public SymbolTable() {
        position = 1;
    }

    public String addTable(String symbol) {
        symbol = symbol.substring(0, symbol.length() -1);

        if(table.get(symbol) == null) {
            table.put(symbol, String.valueOf(position));
            position++;
        }
        return table.get(symbol);
    }

    private String printTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nTabela de símbolos:\n\n");

        table.forEach((symbol, position) ->  sb.append("Símbolo: ").append(symbol).append(" Atributo: ").append(position).append("\n"));

        return sb.toString();
    }

    @Override
    public String toString() {
        return printTable();
    }
}
