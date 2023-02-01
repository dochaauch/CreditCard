package org.example;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DisplayData {
    public static List<Map> parseData(CardStorage cardStorage) {
        List listOfMap = new ArrayList<>();
        for (CardString line: cardStorage) {
            Map<String, String> map = new HashMap<>();
            List row = List.of(line.getCardData().split("_"));
            map.put("cardNumber", (String) row.get(0));
            map.put("firstName", (String) row.get(1));
            map.put("lastName", (String) row.get(2));
            map.put("cvv", (String) row.get(3));
            map.put("expDate", (String) row.get(4));
            listOfMap.add(map);
        }
        return listOfMap;
    }

    private static boolean checkExpDate(String date, int yearDiff) {
        boolean result = true;

        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR) - 2000;
        int monthNow = cal.get(Calendar.MONTH) + 1;

        Pattern pattern = Pattern.compile("(0[1-9]|1[012])\\/(\\d{2})");
        Matcher matcher = pattern.matcher(date);
        while(matcher.find()){
            int month = Integer.parseInt(matcher.group(1));
            int year = Integer.parseInt(matcher.group(2));
            if ( (year > yearNow +yearDiff) || ((year < yearNow) || (year == yearNow && month < monthNow))  ) {
                result = false;
            }
        }
        return result;
    }

    public static void printData(List<Map> listOfMap) {
        int width = 30;
        int repeat = (52 + width)/2;
        int spaceFullName = (width - "full name".length()) /  2;

        lineHorizont(repeat);
        String format = "| %-20s | %-"+ width +"S | %-7s | %-11s |\n";
        System.out.printf(format,
                "Card Number",
                String.format("%" + spaceFullName + "s%s%" + spaceFullName + "s", "","full Name",""),
                "CVV",
                "Expire date");
        lineHorizont(repeat);

        for (Map<String, String> map : listOfMap) {
            String fullName = map.get("firstName") + " " + map.get("lastName");
            int addSpaceL = (width - fullName.length()) / 2;
            int addSpaceR = (width - fullName.length()) / 2;
            addSpaceR = (addSpaceR + addSpaceL + fullName.length() > width) ? addSpaceR - 1 : addSpaceR;
            System.out.printf(format,
                    map.get("cardNumber"),
                    String.format("%" + addSpaceL + "s%s%" + addSpaceR + "s", "",fullName,""),
                    map.get("cvv"),
                    map.get("expDate"));
        }

        lineHorizont(repeat);;
    }

    private static void lineHorizont(int repeat) {
        System.out.println("- ".repeat(repeat));
    }
}
