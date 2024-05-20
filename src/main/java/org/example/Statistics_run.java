package org.example;

import org.example.save_to_disk.Save_to_disk_history;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Statistics_run {
       public static void read_log_mess() throws FileNotFoundException {
            Scanner scanner = new Scanner(new File(Save_to_disk_history.log_log));
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        }


        










}
