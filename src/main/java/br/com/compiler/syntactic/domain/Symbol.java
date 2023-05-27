package br.com.compiler.syntactic.domain;

public class Symbol {
    private String head;
    private String value;
    private boolean isTerminal;
    private boolean isEmpty;

    public Symbol() {
    }

    public Symbol(String head, String value, boolean isTerminal, boolean isEmpty) {
        this.head = head;
        this.value = value;
        this.isTerminal = isTerminal;
        this.isEmpty = isEmpty;
    }

    private String printSymbol() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cabeça: ").append(head).append(" Valor: ").append(value).append(" Terminal: ").append(isTerminal).append(" Vazio: ").append(isEmpty).append("\n");

        return sb.toString();
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
