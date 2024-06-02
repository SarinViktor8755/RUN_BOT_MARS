package org.example;

import java.util.ArrayList;
import java.util.Date;

public class Events {
     static ArrayList<Event> events = new ArrayList<>();


    public static void addevents(){
        events.add(new Event(new Date("07.01.2024"),"Рождественский полумарафон (Омск)"));
        events.add(new Event(new Date("13.01.2024"),"Кроссовый забег \"ZАНУДА TRAIL\" (ПКиО “Советский парк”)"));
        events.add(new Event(new Date("28.01.2024"),"Гонка по снегу “SNOWRACE” (мкр. Крутая Горка)"));
        events.add(new Event(new Date("18.02.2024"),"Зимняя полоса препятствий «Экстремалы уДачи» 900 м (Парк КиО «Зелёный остров»)"));
        events.add(new Event(new Date("23.02.2024"),"Пробег в День защитников Отечества. 15 и 5 км. Бег и северная ходьба. Зеленый остров."));
        events.add(new Event(new Date("03.03.2024"),"Гонка по островам «На абордаж!» 7 км (Парк Победы)"));
        events.add(new Event(new Date("08.03.2024 "),"Крутогорский трейл.  8 км бег или лыжи. Крутая горка."));
        events.add(new Event(new Date("07.04.2024"),"Кроссовый забег «Неугомонный» / Парк КиО «Зелёный остров» 5 и 10 км"));
        events.add(new Event(new Date("21.04.2024"),"\"Приз Ковалёвых\" 7 км (Парк КиО «Зелёный остров»)"));
        events.add(new Event(new Date("28.04.2024 "),"Крутогорский трейл 12 км и 6,7 км"));
        events.add(new Event(new Date("01.05.2024"),"\"Советский трейл\" 4 км / (ПКиО “Советский парк”)"));
        events.add(new Event(new Date("12.05.2024"),"XII часовой забег «Чебурашка» / Парк КиО «30-летя ВЛКСМ»"));
        events.add(new Event(new Date("19.05.2024"),"Всероссийский полумарафон \"Забег.РФ\" (Омск"));
        events.add(new Event(new Date("01.06.2024"),"Зеленый марафон 4,2 км / Организатор \"Сбер\", Лукашевича, 35"));
        events.add(new Event(new Date("11.06.2024"),"\"Самопреодоление\" 3-х и 6 часовые забеги / парк \"Птичья гавань\""));
        events.add(new Event(new Date("16.06.2024"),"Цветочный забег (Омск)"));
        events.add(new Event(new Date("12.07.2024"),"IХ  беговой кубок «Подсолнух уДачи» / Омская область, Омский район, п. Дачный / 14, 7, 21 км"));
        events.add(new Event(new Date("13.07.2024"),"Гонка на выбывание «Хитрый Лис» / Омская область, Омский район, п. Дачный / 7 км - это дистанция одного круга"));
        events.add(new Event(new Date("03.08.2024"),"Сибирский международный марафон (Омск)"));
        events.add(new Event(new Date("10.08.2024"),"ЛЕТО.БЕГ / Организатор \"Спорт в сердце\""));
        events.add(new Event(new Date("25.08.2024"),"Ultra Trail \"SoloRace\" / Омская область, озеро Эбейты / 14, 25, 70 км бег, 35 и 90 км (bike)"));
        events.add(new Event(new Date("07.09.2024"),"Гонка героев (Омск)"));
        events.add(new Event(new Date("26.10.2024"),"\"Три темные мили\" / Организатор \"Спорт в сердце\""));
        events.add(new Event(new Date("03.11.2024"),"Кроссовый забег «Ласосень» / Парк Победы, г. Омск / 5 и 10 км"));
    }

    public static String print_events() {
        addevents();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < events.size(); i++) {
            sb.append(events.get(i).description+ "/n");
        }
        return sb.toString();
    }
}
