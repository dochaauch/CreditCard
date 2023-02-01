package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ReadBinFileTest {
    private Card card1;
    private Card card2;

    @BeforeEach
    void setUp(){
        card1 = new Card("1234123412341234", "Jelena", "Jakobson", 123, "08/23");
        card2 = new Card("5678567856785678", "Marija", "Petrovic", 456, "07/24");
    }

//    @Spy
//    CardStorage expectedStorage;


    @Test
    void read() {
        List<CardString> listOfStrings = Arrays.asList(
                new CardString(card1),
                new CardString(card2)
        );
        CardStorage expectedStorage = new CardStorage();
        expectedStorage.add(new CardString(card1));
        expectedStorage.add(new CardString(card2));
        System.out.println("expectedStorage");
        System.out.println(expectedStorage);

        String fileToRead = "test.bin";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToRead))) {
            oos.writeObject(expectedStorage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CardStorage actualStorage = ReadBinFile.read(fileToRead);
        System.out.println(actualStorage);
        assertEquals(expectedStorage, actualStorage.getStorage());
    }
}