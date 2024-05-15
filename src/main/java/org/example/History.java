package org.example;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.example.MarsSrvice.get_l_to_target;

public class History { //истори€ суммировани€ км
    static public HashMap<Integer, Integer> history_add = new HashMap<>();

    static final Long starTimePoint = System.currentTimeMillis();

    static public void startTime() {

        System.out.println("“екуща€ дата: " + starTimePoint);
    }

    static public long startDeltaTime() {
        return System.currentTimeMillis() - starTimePoint;
    }

    static public int getSumKM() {
        int sum = 0;
        for (HashMap.Entry<Integer, Integer> entry : history_add.entrySet()) {
            sum += entry.getValue();
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + value);
        }
        return sum;
    }

    static public double getSpeed() { // тексущас€ срен€€ скосроть
        double speed = getSumKM() / (History.startDeltaTime() / (double) Constants.HOUR);
        return speed;
    }

    static public String get_ve_to_marsa() {
        //      раст / скорость
        if (getSpeed() == 0.0) return "нет данных";
        double v = get_l_to_target() / getSpeed();
        long ost_v_ch = (long) (v * Constants.HOUR);
        ost_v_ch = ((long) (v * Constants.HOUR)) + System.currentTimeMillis();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date_f = simpleDateFormat.format(new Date(ost_v_ch));

        return date_f;
    }


    static public void print_history() {
        System.out.println(history_add.size());
        history_add.forEach((key, value) -> System.out.println(key + " " + value));
    }

    static public Integer make_changes_to_the_message(int mass_id, String text) {// произвести изменени€ в сообщение
        int sz = 0;// старое значение
        int nz = PasrserString.parsKmString(text);// новое значение

        if (history_add.get(mass_id) != null) sz = history_add.get(mass_id);
        history_add.put(mass_id, nz);
        return (nz - sz);
    }


}
