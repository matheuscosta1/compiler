package br.com.compiler.syntactic.domain;


import java.util.ArrayList;
import java.util.List;

public class Production {
    List<Symbol> productions = new ArrayList<>();
    public void append(Symbol symbol) {
        productions.add(symbol);
    }

    public List<Symbol> getProductions() {
        return productions;
    }

    public void setProductions(List<Symbol> productions) {
        this.productions = productions;
    }
}
