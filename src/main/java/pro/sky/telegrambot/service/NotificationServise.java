package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;

public interface NotificationServise {
    void process(Update update);

    void sendMessage(Long chatId, String messageText);
//    void  process(Update updates);
//
//
//    void process(com.pengrad.telegrambot.model.Update update);
}
