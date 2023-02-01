package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GeneratorTest {

    @Test
    void generateCards() {
        int count = 5;
        CardStorage expectedCardStorage = new CardStorage();
        for (int i = 0; i < count; i++) {
            expectedCardStorage.add(new CardString(
                    new Card("1234 1234 1234 1234",
                            "Jelena",
                            "Jakobson",
                            123,
                            "08/23")));
        }

        CardStorage actualCardStorage = Generator.generateCards(count);

        assertEquals(count, actualCardStorage.getStorage().size(),
                "same size of storage");
    }

}