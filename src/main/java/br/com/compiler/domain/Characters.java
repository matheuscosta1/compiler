package br.com.compiler.domain;

import java.util.ArrayList;
import java.util.List;

public class Characters {
    List<FileCharacter> characters = new ArrayList<>();
    private Integer nextElementIndex;

    private boolean lookAhead;

    public Characters() {
        nextElementIndex = 0;
        lookAhead = false;
    }

    public void append(FileCharacter fileCharacter) {
        characters.add(fileCharacter);
    }

    public List<FileCharacter> getCharacters() {
        return characters;
    }

    public FileCharacter getNextCharacter() {
        if(nextElementIndex < characters.size() && !isLookAhead()) {
            return characters.get(nextElementIndex++);
        }
        return characters.get(nextElementIndex -1);
    }

    public boolean isLookAhead() {
        return lookAhead;
    }

    public void setLookAhead(boolean lookAhead) {
        this.lookAhead = lookAhead;
    }
}
