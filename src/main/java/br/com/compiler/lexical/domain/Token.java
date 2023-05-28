package br.com.compiler.lexical.domain;

public class Token {
    private String name;
    private String attribute;
    private Integer line;
    private Integer column;

    public Token(String name, String attribute, Integer line, Integer column) {
        this.name = name;
        this.attribute = attribute;
        this.line = line;
        this.column = column;
    }

    public Token() {
    }

    public String getName() {
        return name;
    }

    public String getAttribute() {
        return attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                ", attribute='" + attribute + '\'' +
                '}';
    }
}
