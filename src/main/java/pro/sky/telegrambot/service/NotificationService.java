package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;

import java.time.LocalDateTime;

public interface NotificationService {
    void process(Update update);

    void saveEntity(Long chatId, String notification, LocalDateTime alarmDate, LocalDateTime addedAt);

    void sendMessage(Long chatId, String messageText);



}
