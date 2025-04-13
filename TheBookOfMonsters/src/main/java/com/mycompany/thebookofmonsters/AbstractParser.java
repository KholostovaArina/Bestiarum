package com.mycompany.thebookofmonsters;

public abstract class AbstractParser implements Parser {
    protected Parser nextParser;
    protected String format;

    @Override
    public void setNext(Parser parser) {
        this.nextParser = parser;
    }

    @Override
    public void parse(String fileName) {
        if (nextParser != null) {
            nextParser.parse(fileName);
        } 
    }
}