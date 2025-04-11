package com.mycompany.thebookofmonsters;

import java.io.IOException;

public class TheBookOfMonsters {

    public static void main(String[] args) throws IOException, Exception {
        System.out.println("Тестирование UTF-8 вывода: Привет, мир! 😊");

        Controller c = new Controller();
        c.run();
    }
}
