package org.example;

public class Calck {

    public static String main_calck(String text) {

        float[] d = new float[]{5, 10, 15, 21.0975f, 42.195f};
        String[] a = getArreySec(text);
        float time;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < d.length; i++) {
            time = sumSec(tempToSec(a[0], a[1]), d[i]);
            sb.append(String.format("%.2f", d[i]) + timeToString((long) time) + "\n");
        }
        return sb.toString();

    }

    private static String timeToString(long secs) {
        long hour = secs / 3600,
                min = secs / 60 % 60,
                sec = secs / 1 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    private static float sumSec(int sec, float dist) {
        return sec * dist;
    }

    private static int tempToSec(String min, String sec) {
        return (Integer.valueOf(min) * 60) + Integer.valueOf(sec);
    }

    private static String[] getArreySec(String text) {
        String number = text.replaceAll("[^0-9\\:]", "");
        String[] a = number.split(":");
        return a;
    }

}
