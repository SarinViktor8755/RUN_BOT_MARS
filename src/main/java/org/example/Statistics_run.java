package org.example;

import models.PointForStatistic;
import org.example.save_to_disk.Save_to_disk_history;

import java.io.*;
import java.util.*;

public class Statistics_run {


    public static Timer mTimer = new Timer();
    private static int sum_dist = 0;


    public static void create_statistic() {
        String text = read_log_mess();

    }

    public static void parser_log() { // �������� ������ �� ���� ��� ����������
        String jsonString = "{" + read_log_mess() + "}";
     //   System.out.println(jsonString);
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
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static String create_statisstic() {
        StringBuilder sb = new StringBuilder();
        float proc;
        int max_ch = 0;

        ArrayList<PointForStatistic> t = (ArrayList<PointForStatistic>) History.history_statistics.clone();
        PointForStatistic o1, o2;
        for (int i = 0; i < t.size(); i++) {
            for (int j = 0; j < t.size(); j++) {

              //  if (get_name_user(t.get(i)).length() > max_ch) max_ch = get_name_user(t.get(i)).length();
                if (i == j) continue;
                o1 = t.get(i);
                o2 = t.get(j);
                if (o1.equals(o2)) {
                    o1.setDist(o2.getDist() + o1.getDist());
                    o2.setDist(0);
                }
            }
        }

        //  History.history_statistics.get(0).

        Collections.sort(t, new Comparator<PointForStatistic>() {
            @Override
            public int compare(PointForStatistic o1, PointForStatistic o2) {
                return o2.getDist() - o1.getDist();
            }
        });


        get_sum_for_statistic();
        sb.append("������� �������:\n \n ");
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getDist() == 0) continue;
            proc = t.get(i).getDist() / Float.valueOf(sum_dist) * 100;
            sb.append((i + 1) + " " + get_name_user(t.get(i)) + " - " + t.get(i).getDist() + "   _" + ((int) (proc)) + " %\n");
        }
        sb.append("\n������� ��������: " + String.format("%.2f", History.getSpeed()) + " ��/� " + "\n" +
                "��������� ���� ��������: " + History.get_ve_to_marsa() + "\n sum" + sum_dist + " ��");
        return sb.toString();

    }


    private static boolean is_one_week(Long dataPoint, long l){
        Date d = new Date(dataPoint);
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        d.setTime(l);
        Calendar c1 = new GregorianCalendar();
        c1.setTime(d);

        if(c.get(Calendar.WEEK_OF_YEAR)!=c1.get(Calendar.WEEK_OF_YEAR)) return false;
        if(c.get(Calendar.YEAR)!=c1.get(Calendar.YEAR)) return false;


        return true;
    }

    public static String create_statisstic_week() {
        StringBuilder sb = new StringBuilder();
        float proc;
        int max_ch = 0;
        ArrayList<PointForStatistic> t = new ArrayList<>();
        t.clear();
        for (int i = 0; i < History.history_statistics.size(); i++) {
            if(is_one_week(History.history_statistics.get(i).getDataPoint(), System.currentTimeMillis())) t.add(History.history_statistics.get(i));
        }



        //ArrayList<PointForStatistic> t = (ArrayList<PointForStatistic>) History.history_statistics.clone();
        PointForStatistic o1, o2;
        for (int i = 0; i < t.size(); i++) {
            for (int j = 0; j < t.size(); j++) {

                //  if (get_name_user(t.get(i)).length() > max_ch) max_ch = get_name_user(t.get(i)).length();
                if (i == j) continue;
                o1 = t.get(i);
                o2 = t.get(j);
                if (o1.equals(o2)) {
                    o1.setDist(o2.getDist() + o1.getDist());
                    o2.setDist(0);
                }
            }
        }

        //  History.history_statistics.get(0).

        Collections.sort(t, new Comparator<PointForStatistic>() {
            @Override
            public int compare(PointForStatistic o1, PointForStatistic o2) {
                return o2.getDist() - o1.getDist();
            }
        });


        get_sum_for_statistic(t);
        sb.append("������� ������� ������:\n \n ");
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getDist() == 0) continue;
            proc = t.get(i).getDist() / Float.valueOf(sum_dist) * 100;
            sb.append((i + 1) + " " + get_name_user(t.get(i)) + " - " + t.get(i).getDist() + "   _" + ((int) (proc)) + " %\n");
        }
//        sb.append("\n������� ��������: " + String.format("%.2f", History.getSpeed()) + " ��/� " + "\n" +
//                "��������� ���� ��������: " + History.get_ve_to_marsa() + "\n sum" + sum_dist + " ��");
        return sb.toString();

    }

    public static String create_probel(StringBuilder stringBuilder, String name, int max_ch) {
        return "";
    }


    public static String get_name_user(PointForStatistic p) {
        try {
            if (p.getUsername()==null) return p.getUsername();
            if (p.getUsername()==null)  return p.getFirst_name();
            if (p.getUsername()==null)  return p.getLast_name();

            if (!p.getUsername().equals("null")) return p.getUsername();
            if (!p.getFirst_name().equals("null")) return p.getFirst_name();
            if (!p.getLast_name().equals("null")) return p.getLast_name();
            return "NONO";
        }catch (NullPointerException e){
            e.printStackTrace();
           // Save_to_disk_history.load_to_disk_points_for_statistoc();
            return "No name";
        }

    }

    public static void get_sum_for_statistic() {
        Statistics_run.sum_dist = 0;
        for (int i = 0; i < History.history_statistics.size(); i++) {
            Statistics_run.sum_dist += History.history_statistics.get(i).getDist();
        }

    }

    public static void get_sum_for_statistic(ArrayList<PointForStatistic> s) {
        Statistics_run.sum_dist = 0;
        for (int i = 0; i < s.size(); i++) {
            Statistics_run.sum_dist += s.get(i).getDist();
        }

    }


}
