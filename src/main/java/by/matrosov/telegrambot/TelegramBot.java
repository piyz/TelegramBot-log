package by.matrosov.telegrambot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()){

            String user_first_name = message.getChat().getFirstName();
            String user_last_name = message.getChat().getLastName();
            String user_username = message.getChat().getUserName();
            long user_id = message.getChat().getId();

            log(user_first_name,user_last_name, Long.toString(user_id), message.getText(), message.getText());

            sendMsgReply(message,message.getText());
        }
    }

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    private void sendMsgReply(Message message, String text){
        SendMessage s = new SendMessage()
                .setChatId(message.getChatId())
                .setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer){

        System.out.println("--------------------");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/ HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }
}
