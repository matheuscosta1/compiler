package br.com.compiler.lexical.domain;

import java.util.HashMap;
import java.util.Map;

public class Symbol { //TODO: na especificacao do trabalho falou sobre prencher tabela de simbolos com tipo do token, lexema, valor e tipo do dado
    private String tokenType;
    private String lexeme;
    private String value;
    private String dataType;
    private String attribute;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Symbol() {
    }

    public Symbol(String tokenType, String lexeme, String value, String dataType) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.value = value;
        this.dataType = dataType;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
