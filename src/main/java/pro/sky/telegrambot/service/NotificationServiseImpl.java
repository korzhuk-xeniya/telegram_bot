package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.model.Update;


public abstract class NotificationServiseImpl implements NotificationServise {

    private TelegramBot telegramBot;
    @Override
    public void process(Update update) {

        Long chatId = update.message().chat().id();
        String message = update.message().text();
        if (message.equals("/start")) {

            sendWelcomMessage(chatId);

}
    }

    private void sendWelcomMessage(Long chatId) {

        telegramBot.execute(new SendMessage(chatId,
                " Добро пожаловать в бот, который напомнит о важных событиях!"));
    }


}
