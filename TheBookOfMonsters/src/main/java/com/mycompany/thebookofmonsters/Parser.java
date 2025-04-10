package com.mycompany.thebookofmonsters;

public interface Parser {
    public void setNext(Parser parser);
    public void handle(String fileName);
}
