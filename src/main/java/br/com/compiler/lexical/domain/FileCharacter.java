package br.com.compiler.lexical.domain;

public class FileCharacter {

    private String character;
    private Integer line;
    private Integer column;

    public FileCharacter(String character, Integer line, Integer column) {
        this.character = character;
        this.line = line;
        this.column = column;
    }
    public String getCharacter() {
        return character;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    public boolean isEqualsToCharacter(String character) {
        return getCharacter().equals(character);
    }
}
