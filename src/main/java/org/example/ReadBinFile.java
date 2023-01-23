package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class ReadBinFile {
    public static CardStorage read(String fileToRead) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToRead))) {
            List<CardString> storage = (List<CardString>) ois.readObject();
            CardStorage cardStorage = new CardStorage();
            cardStorage.setStorage(storage);
            return cardStorage;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
