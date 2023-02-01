package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Сделать классы
++Карта с полями - номер имя фамилия cvv дата
++Для хранения этих данных ОДНОЙ СТРИНГОЙ
++для генерации ИмениФамилии итд
++для хранения всех данных
++для записи всех данных в файл
++для чтения всех данных из файла
для парсинга регулярными выражениями и там же для предоставления
этих данных при помощи printF в красивом виде

Далее:
Должны быть проверки на корректные даты - формат ММ/ГГ
Все отдельным проектом
*/
public class Main {
    public static void main(String[] args) {
        CardStorage cardStorage = Generator.generateCards(15);
        WriteToFile.writeToFile(cardStorage, "storage.bin");
        CardStorage list =  ReadBinFile.read("storage.bin");
        DisplayData.printData(DisplayData.parseData(list));
    }
}