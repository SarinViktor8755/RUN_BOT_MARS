package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Events {
     static ArrayList<Event> events = new ArrayList<>();


    public static void addevents(){
        events.add(new Event(new Date(reformatString("07.01.2024")),"�������������� ����������� (����)"));
        events.add(new Event(new Date(reformatString("13.01.2024")),"��������� ����� \"Z����� TRAIL\" (���� ���������� ����)"));
        events.add(new Event(new Date(reformatString("28.01.2024")),"����� �� ����� �SNOWRACE� (���. ������ �����)"));
        events.add(new Event(new Date(reformatString("18.02.2024")),"������ ������ ����������� ����������� ����� 900 � (���� ��� ������� ������)"));
        events.add(new Event(new Date(reformatString("23.02.2024")),"������ � ���� ���������� ���������. 15 � 5 ��. ��� � �������� ������. ������� ������."));
        events.add(new Event(new Date(reformatString("03.03.2024")),"����� �� �������� ��� �������!� 7 �� (���� ������)"));
        events.add(new Event(new Date(reformatString("08.03.2024 ")),"������������ �����.  8 �� ��� ��� ����. ������ �����."));
        events.add(new Event(new Date(reformatString("07.04.2024")),"��������� ����� ������������ / ���� ��� ������� ������ 5 � 10 ��"));
        events.add(new Event(new Date(reformatString("21.04.2024")),"\"���� ��������\" 7 �� (���� ��� ������� ������)"));
        events.add(new Event(new Date(reformatString("28.04.2024")),"������������ ����� 12 �� � 6,7 ��"));
        events.add(new Event(new Date(reformatString("01.05.2024")),"\"��������� �����\" 4 �� / (���� ���������� ����)"));
        events.add(new Event(new Date(reformatString("12.05.2024")),"XII ������� ����� ���������� / ���� ��� �30-���� ����̻"));
        events.add(new Event(new Date(reformatString("19.05.2024")),"������������� ����������� \"�����.��\" (����"));
        events.add(new Event(new Date(reformatString("01.06.2024")),"������� ������� 4,2 �� / ����������� \"����\", ����������, 35"));
        events.add(new Event(new Date(reformatString("11.06.2024")),"\"���������������\" 3-� � 6 ������� ������ / ���� \"������ ������\""));
        events.add(new Event(new Date(reformatString("16.06.2024")),"��������� ����� (����)"));
        events.add(new Event(new Date(reformatString("12.07.2024")),"I�  ������� ����� ���������� ����� / ������ �������, ������ �����, �. ������ / 14, 7, 21 ��"));
        events.add(new Event(new Date(reformatString("13.07.2024")),"����� �� ��������� ������� ��� / ������ �������, ������ �����, �. ������ / 7 �� - ��� ��������� ������ �����"));
        events.add(new Event(new Date(reformatString("03.08.2024")),"��������� ������������� ������� (����)"));
        events.add(new Event(new Date(reformatString("10.08.2024")),"����.��� / ����������� \"����� � ������\""));
        events.add(new Event(new Date(reformatString("25.08.2024")),"Ultra Trail \"SoloRace\" / ������ �������, ����� ������ / 14, 25, 70 �� ���, 35 � 90 �� (bike)"));
        events.add(new Event(new Date(reformatString("07.09.2024")),"����� ������ (����)"));
        events.add(new Event(new Date(reformatString("26.10.2024")),"\"��� ������ ����\" / ����������� \"����� � ������\""));
        events.add(new Event(new Date(reformatString("03.11.2024")),"��������� ����� ���������� / ���� ������, �. ���� / 5 � 10 ��"));
    }

    public static String print_events() {
        addevents();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < events.size(); i++) {
            sb.append(events.get(i).description+ "/n");
        }
        return sb.toString();
    }

    private static String reformatString(String inString){
//
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.u", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(inString, formatter);
        System.out.println(date); // 2010-01-02

        return date.toString();
    }
}
