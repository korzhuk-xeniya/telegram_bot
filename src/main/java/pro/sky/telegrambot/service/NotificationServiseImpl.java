package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.LoggerFactory;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public abstract class NotificationServiseImpl implements NotificationServise {

    private TelegramBot telegramBot;
    private NotificationTaskRepository repository;
    private Logger logger = (Logger) LoggerFactory.getLogger(NotificationServiseImpl.class);
    private static Pattern MESSAGE_PATTERN = Pattern.compile("([0-9\\.:\\s]{16})(\\s)(.+)");
    private  static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
    @Override
    public void process(Update update) {


        if (update.message() == null) {
            logger.info("пользователь отправил пустое сообщение");
            return;
        }
        Long chatId = update.message().chat().id();
        String message = update.message().text();
        if (message==null) {
            telegramBot.execute(new SendMessage(chatId, "для начала работы, отправь /start"));
            return;
        }
        if (message.equals("/start")) {
            sendWelcomMessage(chatId);
            return;
}
    }

    private void sendWelcomMessage(Long chatId) {

        telegramBot.execute(new SendMessage(chatId,
                " Добро пожаловать в бот, который напомнит о важных событиях!"));
    }


}
