package com.mycompany.thebookofmonsters;

public abstract class AbstractParser implements Parser {
    protected Parser nextParser;
    protected String format;

    @Override
    public void setNext(Parser parser) {
        this.nextParser = parser;
    }

    @Override
    public void handle(String fileName) {
        if (canHandle(fileName)) {
            parse(fileName);
        } else if (nextParser != null) {
            nextParser.handle(fileName);
        } else {
            System.out.println("No parser found for: " + fileName);
        }
    }

    protected boolean canHandle(String fileName) {
        return fileName.toLowerCase().endsWith("." + format);
    }

    protected abstract void parse(String fileName);
}