package br.com.compiler.syntactic.domain;

public class Symbol {
    private String head;
    private String derivation;
    private boolean isTerminal;
    private boolean isEmpty;

    public Symbol() {
    }

    public Symbol(String head, String value, boolean isTerminal, boolean isEmpty) {
        this.head = head;
        this.derivation = value;
        this.isTerminal = isTerminal;
        this.isEmpty = isEmpty;
    }

    private String printSymbol() {
        return "Cabeça: " + head + " Valor: " + derivation + " Terminal: " + isTerminal + " Vazio: " + isEmpty;
    }

    @Override
    public String toString() {
        return printSymbol();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDerivation() {
        return derivation;
    }

    public void setDerivation(String derivation) {
        this.derivation = derivation;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
