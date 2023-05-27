package br.com.compiler.lexical.utils;

import java.util.Arrays;

public class Constants {
    final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    final String[] NOT_ID = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "b", "d", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "t", "u", "v", "w", "x", "y", "z", "_"};
    final String[] IDS = {"_", "'", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public boolean isCharacterInNumbers(String character) {
        return Arrays.asList(NUMBERS).contains(character);
    }
    public boolean isCharacterNotId(String character) {
        return Arrays.asList(NOT_ID).contains(character);
    }
    public boolean isCharacterInIDS(String character) {
        return Arrays.asList(IDS).contains(character);
    }
}
