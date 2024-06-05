package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Events {
    static ArrayList<Event> events = new ArrayList<>();

    public Events() {

    }

    public static void addevents() {
        events.clear();
        events.add(new Event("07.01.2024", "�������������� ����������� (����)"));
        events.add(new Event("13.01.2024", "��������� ����� \"Z����� TRAIL\" (���� ���������� ����)"));
        events.add(new Event("28.01.2024", "����� �� ����� �SNOWRACE� (���. ������ �����)"));
        events.add(new Event("18.02.2024", "������ ������ ����������� ����������� ����� 900 � (���� ��� ������� ������)"));
        events.add(new Event("23.02.2024", "������ � ���� ���������� ���������. 15 � 5 ��. ��� � �������� ������. ������� ������."));
        events.add(new Event("03.03.2024", "����� �� �������� ��� �������!� 7 �� (���� ������)"));
        events.add(new Event("08.03.2024 ", "������������ �����.  8 �� ��� ��� ����. ������ �����."));
        events.add(new Event("07.04.2024", "��������� ����� ������������ / ���� ��� ������� ������ 5 � 10 ��"));
        events.add(new Event("21.04.2024", "\"���� ��������\" 7 �� (���� ��� ������� ������)"));
        events.add(new Event("28.04.2024", "������������ ����� 12 �� � 6,7 ��"));
        events.add(new Event("01.05.2024", "\"��������� �����\" 4 �� / (���� ���������� ����)"));
        events.add(new Event("12.05.2024", "XII ������� ����� ���������� / ���� ��� �30-���� ����̻"));
        events.add(new Event("19.05.2024", "������������� ����������� \"�����.��\" (����"));
        events.add(new Event("01.06.2024", "������� ������� 4,2 �� / ����������� \"����\", ����������, 35"));
        events.add(new Event("11.06.2024", "\"���������������\" 3-� � 6 ������� ������ / ���� \"������ ������\""));
        events.add(new Event("16.06.2024", "��������� ����� (����)"));
        events.add(new Event("12.07.2024", "I�  ������� ����� ���������� ����� / ������ �������, ������ �����, �. ������ / 14, 7, 21 ��"));
        events.add(new Event("13.07.2024", "����� �� ��������� ������� ��� / ������ �������, ������ �����, �. ������ / 7 �� - ��� ��������� ������ �����"));
        events.add(new Event("03.08.2024", "��������� ������������� ������� (����)"));
        events.add(new Event("10.08.2024", "����.��� / ����������� \"����� � ������\""));
        events.add(new Event("25.08.2024", "Ultra Trail \"SoloRace\" / ������ �������, ����� ������ / 14, 25, 70 �� ���, 35 � 90 �� (bike)"));
        events.add(new Event("07.09.2024", "����� ������ (����)"));
        events.add(new Event("26.10.2024", "\"��� ������ ����\" / ����������� \"����� � ������\""));
        events.add(new Event("03.11.2024", "��������� ����� ���������� / ���� ������, �. ���� / 5 � 10 ��"));
    }

//    public static String print_events() {
//        addevents();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//            sb.append(events.);
//        }
//        return sb.toString();
//    }

    private static String reformatString(String inString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.u", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(inString, formatter);
        System.out.println(date); // 2010-01-02

        return date.toString();
    }

    public static String print() {
        addevents();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < events.size(); i++) {
            //stringBuilder.append("<blockquote>");
            stringBuilder.append(event_to_string(events.get(i)) + "\n" + "\n");

            //  stringBuilder.append("</blockquote>");
        }
        return stringBuilder.toString();


    }

    public static String event_to_string(Event event) {
        String uri = null;
        Date date_td = new Date();
        try {
            SimpleDateFormat formatte = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date a = formatte.parse(event.data_event);
            long daysBetween = calculateDaysBetween(date_td, a);

            if(event.getUti()!=null) {
            uri = "<a href=\"" + event.getUti() + "\">�����������</a>";
            System.out.println(uri);
            } else uri = "";

            if (daysBetween < 0)
                return ("\uD83D\uDD3A" + formatte.format(a) + " " + event.description);
            else if (daysBetween == 0)
                return ("<b>\uD83C\uDFC3\uD83C\uDFC3\u200D?\uFE0F (" + daysBetween + " ����) " + formatte.format(a) + " " + event.description + "</b>") +  uri;
            else if (daysBetween == 1)
                return ("\uD83D\uDC49 (" + daysBetween + " ����) " + formatte.format(a) + " " + event.description) +  uri;
            else if (daysBetween <= 4)
                return ("\uD83D\uDC49 (" + daysBetween + " ���)" + formatte.format(a) + " " + event.description)  +  uri;
            else if (daysBetween <= 30)
                return ("\uD83D\uDC49 (" + daysBetween + " ����)" + formatte.format(a) + " " + event.description) +  uri;
            else
                return ("(" + daysBetween + " ����)" + formatte.format(a) + event.description) +  uri;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long calculateDaysBetween(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return java.time.temporal.ChronoUnit.DAYS.between(localDate1, localDate2);
    }


}
