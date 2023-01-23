package org.example;

import com.github.javafaker.Faker;
import lombok.ToString;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
public class Generator{
    public static CardString createDataForCard(){
        Faker faker = new Faker();

        String cardNumber = faker.random().nextInt(1000, 9999).toString().repeat(4);

        Date dateFrom = null;
        try {
            dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date dateTo = null;
        try {
            dateTo = new SimpleDateFormat("yyyy-MM-dd").parse("2027-12-31");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date expDateRaw = faker.date().between(dateFrom, dateTo);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expDate = dateFormat.format(expDateRaw);
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})-(\\d{1,2})-(\\d{2})");
        Matcher matcher = pattern.matcher(expDate);

        while (matcher.find()) {
            String month = matcher.group(3).length() < 2 ? "" + 0 + matcher.group(3) : matcher.group(3);
            expDate =  month + "/" + matcher.group(2);
        }

        Card card = new Card(cardNumber,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.random().nextInt(100,999),
                expDate);
        return new CardString(card);
    }

    public static CardStorage generateCards(int count) {
        CardStorage cardStorage = new CardStorage();
        for (int i = 0; i < count; i++) {
            CardString g = new Generator().createDataForCard();
            cardStorage.add(g);
        }
        return cardStorage;
    }
}
