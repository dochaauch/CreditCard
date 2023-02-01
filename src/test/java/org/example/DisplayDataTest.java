package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DisplayDataTest {
    private Map<String, String> map1;
    private Map<String, String> map2;
    private Map<String, String> map3;
    private Card card1;
    private Card card2;


    @BeforeEach
    void setUp(){
        card1 = new Card("1234 1234 1234 1234", "Jelena", "Jakobson", 123, "08/23");
        card2 = new Card("5678 5678 5678 5678", "Marija", "Petrovic", 456, "07/24");

        map1 = new HashMap<>();
        map1.put("cardNumber", "1234 1234 1234 1234");
        map1.put("firstName", "Jelena");
        map1.put("lastName", "Jakobson");
        map1.put("cvv", "123");
        map1.put("expDate", "08/23");

        map2 = new HashMap<>();
        map2.put("cardNumber", "5678 5678 5678 5678");
        map2.put("firstName", "Marija");
        map2.put("lastName", "Petrovic");
        map2.put("cvv", "456");
        map2.put("expDate", "07/24");

        map3 = new HashMap<>();
        map3.put("cardNumber", "1234567890123456");
        map3.put("firstName", "John");
        map3.put("lastName", "Doe");
        map3.put("cvv", "123");
        map3.put("expDate", "01/25");
    }

    @Test
    void testParseData() {
        CardStorage cardStorage = new CardStorage();
        cardStorage.add(new CardString(card1));
        cardStorage.add(new CardString(card2));

        List<Map> expectedList = new ArrayList<>();
        expectedList.add(map1);
        expectedList.add(map2);

        List<Map> actualList = DisplayData.parseData(cardStorage);
        assertEquals(expectedList, actualList);
    }

    @Test
    void printData() {
        // create a sample data
        List<Map> listOfMap = new ArrayList<>();
        listOfMap.add(map3);

        // redirect System.out to a string stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        // call the method to test
        DisplayData.printData(listOfMap);

        // get the output from the string stream
        String output = baos.toString();

        // compare the output with the expected result
        String expected =
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n" +
                "| Card Number          |           FULL NAME            | CVV     | Expire date |\n" +
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n" +
                "| 1234567890123456     |            JOHN DOE            | 123     | 01/25       |\n" +
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n";

        System.out.println(expected);
        System.out.println(output);
        assertEquals(expected, output);

        // reset System.out
        System.setOut(System.out);
        }

}