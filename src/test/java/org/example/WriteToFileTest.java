package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WriteToFileTest {
    @Mock
    private CardStorage cardStorage;

    @Mock
    private Card card;


    @Test
    public void testWriteToFile() throws IOException, ClassNotFoundException {
        String fileNameWrite = "test.txt";
        List<CardString> storage = new ArrayList<>();
        storage.add(new CardString(card));
        when(cardStorage.getStorage()).thenReturn(storage);
        WriteToFile.writeToFile(cardStorage, fileNameWrite);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileNameWrite))) {
            List<CardString> result = (List<CardString>) ois.readObject();
            assertEquals(storage.size(), result.size());
            for (int i = 0; i < storage.size(); i++) {
                assertEquals(storage.get(i).getCardData(), result.get(i).getCardData());
            }
        } finally {
            new File(fileNameWrite).delete();
        }
    }
}