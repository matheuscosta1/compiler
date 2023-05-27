package br.com.compiler.syntactic.domain;

import java.util.HashMap;
import java.util.List;

public class ProductionTable {
    HashMap<Integer, Production> productions = new HashMap<>();

    public void addTable(Integer position, Production production) {
        productions.put(position, production);
    }

    public List<Symbol> getProductions(Integer position) {
        Production production = productions.get(position);
        return production.productions;
    }
    public void setProductions(HashMap<Integer, Production> productions) {
        this.productions = productions;
    }
}
