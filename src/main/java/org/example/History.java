package org.example;

import java.util.HashMap;

public class History { //история суммирования км
    static public HashMap<Integer, Integer> history_add = new HashMap<>();

    static public void print_history() {
        System.out.println(history_add.size());
        history_add.forEach((key, value) -> System.out.println(key + " " + value));
    }

    static public Integer make_changes_to_the_message(int mass_id, String text) {// произвести изменения в сообщение
        int sz = 0;// старое значение
        int nz = PasrserString.parsKmString(text);// новое значение
        if (history_add.get(mass_id) != null) sz = history_add.get(mass_id);
        history_add.put(mass_id, nz);
        return (nz-sz);
    }


}
