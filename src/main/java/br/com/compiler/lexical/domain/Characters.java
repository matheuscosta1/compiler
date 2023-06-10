package br.com.compiler.lexical.domain;

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

    public FileCharacter getNextCharacter() {
        if(nextElementIndex < characters.size() && !isLookAhead()) {
            return characters.get(nextElementIndex++);
        }
        setLookAhead(false); //se não cair no if de cima quer dizer que está em lookahead, só que o próximo token deve continuar normal, pegando o próximo caracter então lookahead volta pra false
        return characters.get(nextElementIndex - 1);
    }

    public boolean isLookAhead() {
        return lookAhead;
    }

    public void setLookAhead(boolean lookAhead) {
        this.lookAhead = lookAhead;
    }
}
