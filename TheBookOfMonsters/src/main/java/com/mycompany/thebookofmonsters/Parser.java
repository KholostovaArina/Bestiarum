package com.mycompany.thebookofmonsters;

public interface Parser {
    void setNext(Parser parser);
    void handle(String fileName);
}
