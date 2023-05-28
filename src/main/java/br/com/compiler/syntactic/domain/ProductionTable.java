package br.com.compiler.syntactic.domain;

import java.util.HashMap;
import java.util.List;

public class ProductionTable {
    HashMap<Integer, Production> table = new HashMap<>();

    public void addTable(Integer position, Production production) {
        table.put(position, production);
    }

    public List<Symbol> getProductions(Integer position) {
        Production production = table.get(position);
        return production.productions;
    }
}
