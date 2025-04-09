package com.mycompany.thebookofmonsters;

import java.io.IOException;

public class TheBookOfMonsters {

    public static void main(String[] args) throws IOException {
         String path = View.chooseTxtFile();
         TxtConverter.convertFile(path);
    }
}
