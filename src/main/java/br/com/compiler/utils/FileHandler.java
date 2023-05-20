package br.com.compiler.utils;

import br.com.compiler.domain.Characters;
import br.com.compiler.domain.FileCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class FileHandler {
    public static Characters readFile(String fileName) throws IOException {
        InputStream inputStream = Objects.requireNonNull(FileHandler.class.getClassLoader().getResource(fileName)).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Characters characters = new Characters();

        String line;

        int lineCounter = 1;
        int columnCounter = 0;

        while ((line = reader.readLine()) != null) {
            columnCounter = 1;
            for (char character : line.toCharArray()) {
                characters.append(new FileCharacter(String.valueOf(character), lineCounter, columnCounter));
                columnCounter++;
            }
            lineCounter++;
        }

        characters.append(new FileCharacter("EOF", ++lineCounter, 0)); //TODO: Remover esse EOF no final de tudo

        return characters;
    }
}
