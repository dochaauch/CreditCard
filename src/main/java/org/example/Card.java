package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Card {
    private String cardNumber;
    private String firstName;
    private String familyName;
    private int cvv;
    private String date;
}


@Setter
@Getter
@ToString
class CardString implements Serializable{
    private String cardData;

    CardString(Card card){
        this.cardData = card.getCardNumber() + "_"
                + card.getFirstName() + "_"
                + card.getFamilyName() + "_"
                + card.getCvv() + "_"
                + card.getDate();
    }
}


@ToString
@Setter
@Getter
class CardStorage implements Iterable<CardString>, Serializable{
    private List<CardString> storage;
    public CardStorage() {
        storage = new ArrayList<>();
    }

    public void add(CardString cardString){
        storage.add(cardString);
    }

    @Override
    public Iterator<CardString> iterator() {
        return storage.iterator();
    }
}


