package br.com.compiler.domain;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private final List<Symbol> table = new ArrayList<>();

    public SymbolTable() {
    }

    public void addTable(Symbol symbol) {
        symbol.setPosition(table.size() + 1);
        table.add(symbol);
    }

    private String printTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tabela de símbolos:\n\n");
        table.forEach(symbol -> sb.append("Símbolo: ").append(symbol.name).append(" Atributo: ").append(symbol.attribute).append(" Posição: ").append(symbol.position).append("\n"));
        return sb.toString();
    }

    @Override
    public String toString() {
        return printTable();
    }

    public static class Symbol {
        private String name;
        private String attribute;
        private Integer position;

        public Symbol(String name, String attribute) {
            this.name = name;
            this.attribute = attribute;
        }

        public Symbol(String name, String attribute, Integer position) {
            this.name = name;
            this.attribute = attribute;
            this.position = position;
        }

        public Symbol() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }
    }
}
