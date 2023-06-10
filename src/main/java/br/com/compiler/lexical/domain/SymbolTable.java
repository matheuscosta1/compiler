package br.com.compiler.lexical.domain;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable { //TODO: na especificacao do trabalho falou sobre prencher tabela de simbolos com tipo do token, lexema, valor e tipo do dado
    private final HashMap<String, Symbol> table = new HashMap<>();

    // identificador = pedro
    // key = pedro
    // Symbol {  private String tokenType = identificador
    //    private String lexeme = pedro
    //    private String value = pedro
    //    private String dataType = ''
    //    private String attribute = 1 }

    private Integer position;

    public SymbolTable() {
        position = 1;
    }

    public String getSymbolValue(String attribute) {
        for (Map.Entry<String, Symbol> stringStringEntry : table.entrySet()) {
            if(stringStringEntry.getValue().getAttribute().equals(attribute)) {
                return stringStringEntry.getKey();
            }
        }
        return "";
    }


    public Symbol getSymbol(String attribute) {
        for (Map.Entry<String, Symbol> stringStringEntry : table.entrySet()) {
            if(stringStringEntry.getValue().getAttribute().equals(attribute)) {
                return stringStringEntry.getValue();
            }
        }
        return new Symbol();
    }

    public boolean isSymbolOnTable(String attribute) {
        for (Map.Entry<String, Symbol> stringStringEntry : table.entrySet()) {
            if(stringStringEntry.getValue().getAttribute().equals(attribute)) {
                return true;
            }
        }
        return false;
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

        table.forEach((key, value) ->  sb.append("Símbolo: ").append(key).append(" Tipo do Token: ").append(value.getTokenType()).append(" Lexema: ").append(value.getLexeme()).append(" Valor: ").append(value.getValue()).append(" Tipo dado: ").append(value.getDataType()).append(" Atributo: ").append(value.getAttribute()).append("\n"));

        return sb.toString();
    }

    @Override
    public String toString() {
        return printTable();
    }
}
