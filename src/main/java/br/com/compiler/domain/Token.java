package br.com.compiler.domain;

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

    public void setName(String name) {
        this.name = name;
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

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                ", attribute='" + attribute + '\'' +
                '}';
    }
}
