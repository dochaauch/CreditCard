package org.example;

import java.io.*;

public class WriteToFile {

    public static void writeToFile(CardStorage cardStorage, String fileNameWrite){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileNameWrite))) {
            oos.writeObject(cardStorage.getStorage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
