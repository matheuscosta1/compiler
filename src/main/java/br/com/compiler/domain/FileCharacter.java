package br.com.compiler.domain;

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

    public void setCharacter(String character) {
        this.character = character;
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

    public boolean isEqualsToCharacter(String character) {
        return getCharacter().equals(character);
    }
}
