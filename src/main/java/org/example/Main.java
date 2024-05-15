package org.example;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.ArrayList;
import java.util.Random;

import static org.example.History.getSumKM;

import static org.example.History.get_ve_to_marsa;
import static org.example.PasrserString.parsKmString;


public class Main {
    static String BOT_TOKKEN_test = "7039553340:AAHCuowlWMQltfoQNwXOg6MUQ3srtm95N0o";
    static String BOT_TOKKEN = "6552221670:AAFpvfvUmNAEfkhkDUS5nt8HCl3N8906soc";

    static public int block_lskala = 0;

    static public long km = 0; // ??????????? ?????????
    static public final float Distance_Earth_Mars = 54_600; // ???? ?????????


    static public ArrayList<String> Admins_nik = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Start_BOT_RUN");
        History.startTime();
        add_admins();
        start_distanc(args);
        TelegramBot bot = new TelegramBot(BOT_TOKKEN);
        ////////////////////
        bot.setUpdatesListener(updates -> {
            Update mes;
            for (int i = 0; i < updates.size(); i++) {
                try {
                    //History.startTimegetDelta();
                 //   System.out.println(getSumKM());
                    double h = History.startDeltaTime() / (double)Constants.HOUR;
                   // System.out.println("HOUR  " + h);
                  //  System.out.println("speed : "+History.getSpeed());
                   // System.out.println("prog" + get_ve_to_marsa());
                  //  get_arrival_forecast();
                    mes = updates.get(i);


                    if (mes.editedMessage() != null) {
                        int m_id = mes.editedMessage().messageId();
                        String new_text = mes.editedMessage().caption();

                       //  System.out.println("editedMessage!!!11  0");
                        if (mes.editedMessage().caption() != null) new_text = mes.editedMessage().caption();
                      //    System.out.println(mes);
                        if (!PasrserString.fineKM(new_text)) break;
                       //    System.out.println("editedMessage!!!11  ");

                        int km_delta = History.make_changes_to_the_message(m_id, new_text);
                        //      System.out.println("editedMessage!!!222");
                        if (km_delta == 0) break;
                        Main.km += km_delta;
                        //History.print_history();
                        //      System.out.println("editedMessage!!!333 :: " + mes.editedMessage().chat().id());
                        // bot.execute(new SendMessage(mes.editedMessage().chat().id(), "ISPRAV"));
                        bot.execute(new SendMessage(mes.editedMessage().chat().id(), "����������::\n" + MarsSrvice.calculate_percentage(km, parsKmString(new_text))).replyToMessageId(m_id));

                        break;
                        // System.out.println("---");

                    }

                    long td = Main.km;
                    long chatId = mes.message().chat().id();
                    User user = mes.message().from();
                    String text_mes;
                    boolean isPhoto = false;


                    if (mes.message().caption() != null) {
                        text_mes = mes.message().caption();
                        isPhoto = true;
                    } else text_mes = mes.message().text();

                    //     System.out.println("text_mes   " + text_mes);
                    //   bot.execute(new SendMessage(chatId,"---").replyToMessageId(mes.message().messageId()));


                    if (PasrserString.distanc_reader(text_mes, user)) {
                        bot.execute(new SendMessage(chatId, user.username() + " �������� �������� " + td + " �� " + Main.km + " "));
                    }


                    if (PasrserString.fineKM(text_mes)) {

                        if (!isPhoto) {
                            give_my_photo(chatId, bot, mes.message().messageId());
                        } else {
                           // System.out.println("11111");
                            ask_km(text_mes, bot, chatId, user, mes.message().messageId());
                        }
                    }

                       check_block(mes,bot);
                   //   System.out.println(mes);
                       lediskala_Del(bot, mes);
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

    ///// �������� �������� ��������
    static public void ask_km(String text_mes, TelegramBot bot, long chatId, User user, Integer mes_id) {
        int user_run = (int) parsKmString(text_mes);
        if (user_run <= 0) return;
        km += Long.valueOf(user_run);
        bot.execute(new SendMessage(chatId, MarsSrvice.calculate_percentage(km, user_run)).replyToMessageId(mes_id));
//        bot.execute(new SendMessage(chatId,"\n���� ������� �������� : "+History.getSpeed() + " ��/� "));
//
//        bot.execute(new SendMessage(chatId,"\n �� ����� ����� : "+ History.get_ve_to_marsa()));

        Users.add_km_for_user(user_run, user);

        History.history_add.put(mes_id, user_run);


    }

    //// �������� �������� ����
    static public void give_my_photo(long chatId, TelegramBot bot, int mes_id) {
        bot.execute(new SendMessage(chatId, "���� �� �� ���������, �������, �� ��������, ������ ���� ��� ����� � ��������\uD83D\uDE09").replyToMessageId(mes_id));
    }

    static public void start_distanc(String[] args) {
        //System.out.println(args[0]);
        try {
            km = Long.parseLong(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            km = 0;
        }

    }

    static public void lediskala_Del(TelegramBot bot, Update mes) {
        int id_ls = 2008008852;
        int id_my= 299695014;
    //    System.out.println(mes.editedMessage().text()==null);
       // if (mes.editedMessage().caption() != null) return;
        System.out.println(block_lskala);
        if (block_lskala == 0) return;


        //   System.out.println("qweqe");


        if (mes.message().from().id() == id_ls) {
            String chatId = String.valueOf(mes.message().chat().id());
            Integer messageId = mes.message().messageId();
            String text = mes.message().text();
            //if(mes.editedMessage().caption()!= null)  text = mes.editedMessage().caption();


//            DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
//            bot.execute(deleteMessage);
            if (block_lskala == 2) {
                StringBuilder sb = new StringBuilder();
                sb.append("\uD83C\uDD7B\uD83C\uDD74\uD83C\uDD73\uD83C\uDD78\uD83C\uDD82\uD83C\uDD7A\uD83C\uDD70\uD83C\uDD7B\uD83C\uDD70\n");
                int kol = text.length() / 3;
                if (kol < 1) kol = 2;
                for (int i = 0; i < kol; i++) {
                    if (randomBoolean(.5f)) sb.append("��� ");
                    else sb.append("��� \uD83E\uDD84\uD83E\uDD84\uD83E\uDD84");
                }

                bot.execute(new SendMessage(chatId, sb.toString()));
            }
        }
    }


    static public void check_block(Update mes,TelegramBot bot) {
        if (mes.message().from().id() != 299695014) return;
        String text = mes.message().text();
        if (text.equals("/ls0")) {block_lskala = 0;delMess(mes,bot);}
        if (text.equals("/ls1")) {block_lskala = 1;delMess(mes,bot);}
        if (text.equals("/ls2")) {block_lskala = 2;delMess(mes,bot);}
    }

    static private void delMess(Update mes,TelegramBot bot){
        String chatId = String.valueOf(mes.message().chat().id());
        Integer messageId = mes.message().messageId();
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        bot.execute(deleteMessage);

    }

    static public void delate_mess(TelegramBot bot, Update mes) {
        // mes.deletedBusinessMessages();
        String chatId = String.valueOf(mes.message().chat().id());
        Integer messageId = mes.message().messageId();
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        bot.execute(deleteMessage);
    }

    static public void make_changes(TelegramBot bot, Update mes) {
        //  mes.deletedBusinessMessages();
        String chatIdd = String.valueOf(mes.message().chat().id());
        Integer messageId = mes.message().messageId();
        DeleteMessage deleteMessage = new DeleteMessage(chatIdd, messageId);
        bot.execute(deleteMessage);
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

    static public boolean randomBoolean(float chance) {
        return random() < chance;
    }

    static public float random() {
        Random random = new Random();
        return random.nextFloat();
    }


}