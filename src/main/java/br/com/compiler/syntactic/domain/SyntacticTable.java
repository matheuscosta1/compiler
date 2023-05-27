package br.com.compiler.syntactic.domain;

import java.util.HashMap;

public class SyntacticTable {
    HashMap<String, HashMap<String, Integer>> table = new HashMap<>();

    public Integer getProductionDerivation(String derivation, String tokenName) {
        return table.get(derivation).get(tokenName);
    }

    public HashMap<String, HashMap<String, Integer>> getTable() {
        return table;
    }

    public void setTable(HashMap<String, HashMap<String, Integer>> table) {
        this.table = table;
    }
}
