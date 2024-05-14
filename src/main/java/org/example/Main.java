package org.example;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.ArrayList;

import static org.example.PasrserString.parsKmString;


public class Main {
    static String BOT_TOKKEN_test = "7039553340:AAHCuowlWMQltfoQNwXOg6MUQ3srtm95N0o";
    static String BOT_TOKKEN = "6552221670:AAFpvfvUmNAEfkhkDUS5nt8HCl3N8906soc";

    static public long km = 0; // ??????????? ?????????
    static public final float Distance_Earth_Mars = 54_600; // ???? ?????????


    static public ArrayList<String> Admins_nik = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Start_BOT_RUN");
        add_admins();
        start_distanc(args);
        TelegramBot bot = new TelegramBot(BOT_TOKKEN_test);
        ////////////////////
        bot.setUpdatesListener(updates -> {
            Update mes;
            for (int i = 0; i < updates.size(); i++) {
                try {

                    mes = updates.get(i);
                 //   System.out.println(mes);
//                    System.out.println("+++" + mes.message().caption());
//                    System.out.println("----" + mes.message().text());
                    if (mes.editedMessage() != null) {

                        int m_id = mes.editedMessage().messageId();
                        String new_text = mes.editedMessage().text();

                        // System.out.println("editedMessage!!!11  0");
                        if(mes.editedMessage().caption()!= null) new_text = mes.editedMessage().caption();
                      //  System.out.println(mes);
                        if (!PasrserString.fineKM(new_text)) break;
                        //   System.out.println("editedMessage!!!11  ");

                        int km_delta = History.make_changes_to_the_message(m_id, new_text);
                     //      System.out.println("editedMessage!!!222");
                        Main.km += km_delta;
                        //History.print_history();
                     //      System.out.println("editedMessage!!!333 :: " + mes.editedMessage().chat().id());
                        // bot.execute(new SendMessage(mes.editedMessage().chat().id(), "ISPRAV"));
                        bot.execute(new SendMessage(mes.editedMessage().chat().id(), "Исправлено::\n" + MarsSrvice.calculate_percentage(km, parsKmString(new_text))).replyToMessageId(m_id));
                        History.print_history();
                        break;
                        // System.out.println("---");

                    }

                    long td = Main.km;
                    long chatId = mes.message().chat().id();
                    User user = mes.message().from();
                    String text_mes;

                    if (mes.message().caption() != null) text_mes = mes.message().caption();
                    else text_mes = mes.message().text();

                    System.out.println("text_mes   " + text_mes);
                    //   bot.execute(new SendMessage(chatId,"---").replyToMessageId(mes.message().messageId()));


                    if (PasrserString.distanc_reader(text_mes, user)) {
                        bot.execute(new SendMessage(chatId, user.username() + " Исправил значение " + td + " на " + Main.km + " "));
                    }


                    if (PasrserString.fineKM(text_mes)) {
//                        int user_run = (int) PasrserString.parsKmString(text_mes);
//                        if (user_run <= 1) break;
//                        km += Long.valueOf(user_run);
//                        bot.execute(new SendMessage(chatId, MarsSrvice.calculate_percentage(km, user_run)));
//                        Users.add_km_for_user(user_run, user);
                        ask_km(text_mes, bot, chatId, user, mes.message().messageId());

                    }


                } catch (NullPointerException | NumberFormatException e) {

                }

            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;

        }, e -> {
            if (e.response() != null) {
                // got bad response from telegram
                e.response().errorCode();
                e.response().description();
            } else {
                // probably network error
                e.printStackTrace();
            }
        });


    }

    ///// ОСНОВНОЙ отправка собщения
    static public void ask_km(String text_mes, TelegramBot bot, long chatId, User user, Integer mes_id) {
        int user_run = (int) parsKmString(text_mes);
        if (user_run <= 0) return;
        km += Long.valueOf(user_run);
        bot.execute(new SendMessage(chatId, MarsSrvice.calculate_percentage(km, user_run)).replyToMessageId(mes_id));
        Users.add_km_for_user(user_run, user);

        History.history_add.put(mes_id, user_run);


    }

    static public void start_distanc(String[] args) {
        //System.out.println(args[0]);
        try {
            km = Long.parseLong(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            km = 0;
        }

    }

    static public boolean check_photo(Update mes) {
        if (mes.message().photo() == null) return false;
        else return true;
    }


    static public void add_admins() {
        Admins_nik = new ArrayList<>();
        Admins_nik.add("Vity55");
        Admins_nik.add("Firefighter55");
        Admins_nik.add("Anton_Kipchoge");

    }

}