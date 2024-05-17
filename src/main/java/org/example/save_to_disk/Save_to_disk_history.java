package org.example.save_to_disk;

import org.example.History;
import org.example.Main;

import java.io.*;
import java.util.HashMap;

public class Save_to_disk_history {

    static String log_histort = "points.txt";
    static String log_log = "log.txt";

    public static void save_to_disk_points() {
        StringBuilder sb = new StringBuilder();
        sb.append(History.starTimePoint + ",");
        sb.append(Main.km + "\n");
        History.history_add.forEach((key, value) -> sb.append(key + "," + value + "\n"));
        //  String str = String.valueOf(start_point_time);
        String fileName = log_histort;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void read_to_disk_history() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(log_histort));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int i = 0;
            System.out.println("Read file history");
            while (line != null) {
                parser_line(i,line);
                i++;
                System.out.println(i+": "+line);
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();

            }
       //     String everything = sb.toString();
          //  History.print_history();
            System.out.println(Main.km + "  "+ History.starTimePoint);
            br.close();

    }



    public static void parser_line(int nl, String line){
        String[] split_l = line.split(",");
        if(nl == 0) {
            History.starTimePoint = Long.parseLong(split_l[0]);
            Main.km = Long.parseLong(split_l[1]);
        }else {
            History.history_add.put(Integer.valueOf(split_l[0]),Integer.valueOf(split_l[1]));
        }
    }

    public static void addMesToFile(String messs){
        try {
        Writer output;
        output = new BufferedWriter(new FileWriter(log_log));  //clears file every time
        output.append(messs+"\n");
        output.close();
        }catch (Exception e){}
    }



}
