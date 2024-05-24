package org.example;

import org.example.save_to_disk.Save_to_disk_history;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Scanner;
import java.util.Timer;

public class Statistics_run {


    public static Timer mTimer = new Timer();


    public static void create_statistic() {
        String text = read_log_mess();

    }

    public static void parser_log() { // рабирает строку из логу для статистики
        String jsonString  = "{" + read_log_mess() + "}";
        System.out.println(jsonString);
       // JSONObject obj = new JSONObject(jsonString);



    }


    public static String read_log_mess() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Save_to_disk_history.log_log));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            //   System.out.println(everything);
            return everything;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
