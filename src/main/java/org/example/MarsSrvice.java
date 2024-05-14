package org.example;

import java.util.Locale;

import static org.example.Main.Distance_Earth_Mars;

public class MarsSrvice {

    static final String ballEmoji = "\uD83D\uDC4D";
    static String calculate_percentage(long km, int user_run) {

        float res = (km / Distance_Earth_Mars) * 100;
        if (res <= 100)
            return "Принято " + user_run + " км "+ create_emogi(user_run)+"\nпробежали " + String.format(Locale.US, "%,d", km) + " км :" + String.format("%.2f", res) + "%  от цели \nДо Марса : " + String.format(Locale.US, "%,d", (int)((Distance_Earth_Mars - km))) + " km \n" + create_track_bar(21);
        else return "Финиш!!!";
    }


    static String create_track_bar(int length_bar) {
        int point = (int)map(0, Distance_Earth_Mars,0,length_bar,Main.km);
        StringBuilder sb = new StringBuilder();
        sb.append("З|");
        for (int i = 0; i < length_bar; i++) {
            if(point == i ) sb.append("*(МЫ)>");
            if(point < i ) sb.append("-");
            if(point > i ) sb.append("=");

        }
        sb.append("|М");
        return sb.toString();
    }

    static String create_emogi(long km) {
        StringBuilder sb = new StringBuilder();
        int kol = (int)(km/4f);
        if (kol > 7) kol = 7;
        if (kol < 1) kol = 1;
        for (int i = 0; i < kol; i++) {
            sb.append(ballEmoji);
        }


        return sb.toString();
    }


    static public float map (float inRangeStart, float inRangeEnd, float outRangeStart, float outRangeEnd, float value) {
        return outRangeStart + (value - inRangeStart) * (outRangeEnd - outRangeStart) / (inRangeEnd - inRangeStart);
    }
}
