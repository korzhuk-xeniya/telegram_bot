package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import org.springframework.scheduling.annotation.Scheduled;

public interface NotificationServise {
    void process(Update update);

    void sendMessage(Long chatId, String messageText);



}
