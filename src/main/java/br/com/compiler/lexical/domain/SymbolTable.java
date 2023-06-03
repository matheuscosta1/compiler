package br.com.compiler.lexical.domain;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable { //TODO: na especificacao do trabalho falou sobre prencher tabela de simbolos com tipo do token, lexema, valor e tipo do dado
    private final HashMap<String, Symbol> table = new HashMap<>();
    private Integer position;

    public SymbolTable() {
        position = 1;
    }

    public String getSymbol(String attribute) {
        for (Map.Entry<String, Symbol> stringStringEntry : table.entrySet()) {
            if(stringStringEntry.getValue().getAttribute().equals(attribute)) {
                return stringStringEntry.getKey();
            }
        }
        return "";
    }

    public Symbol addTable(Symbol symbol) {
        if(table.get(symbol.getLexeme()) == null) {
            symbol.setAttribute(String.valueOf(position));
            table.put(symbol.getLexeme(), symbol);
            position++;
        }
        return table.get(symbol.getLexeme());
    }

    private String printTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nTabela de símbolos:\n\n");

        table.forEach((key, value) ->  sb.append("Símbolo: ").append(key).append(" Tipo do Token: ").append(value.getTokenType()).append(" Lexema: ").append(value.getLexeme()).append(" Valor: ").append(value.getValue()).append(" Tipo dado: ").append(value.getDataType()).append("\n"));

        return sb.toString();
    }

    @Override
    public String toString() {
        return printTable();
    }
}
