package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public  class NotificationServiseImpl implements NotificationServise {

    private TelegramBot telegramBot;
    private NotificationTaskRepository repository;
    private Logger logger = (Logger) LoggerFactory.getLogger(NotificationServiseImpl.class);
    private static Pattern MESSAGE_PATTERN = Pattern.compile("([0-9\\.:\\s]{16})(\\s)(.+)");
    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");

    @Override
    public void process(Update update) {


        if (update.message() == null) {
            logger.info("пользователь отправил пустое сообщение");
            return;
        }
        Long chatId = update.message().chat().id();
        String message = update.message().text();
        if (message == null) {
            telegramBot.execute(new SendMessage(chatId, "для начала работы, отправь /start"));
            return;
        }
        if (message.equals("/start")) {
            sendWelcomeMessage(chatId);
            return;
        }
        Matcher matcher = MESSAGE_PATTERN.matcher(message);
        if (matcher.find()) {
            dateFormatterValidation(chatId, matcher);
        } else {
            telegramBot.execute(new SendMessage(chatId,
                    "Добавление напоминания возможно только в следующем формате: 'дд.мм.гггг чч:мм' текст напоминания"));
            return;
        }
        LocalDateTime alarmDate = LocalDateTime.parse(matcher.group(1), DATE_TIME_FORMATTER);
        String notification = matcher.group(3);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        saveEntity(chatId, notification, alarmDate, now);

    }

    private void dateFormatterValidation(Long chatId, Matcher matcher) {//нужен тест
        String dateString = matcher.group(1);
        try {
            LocalDateTime alarmDate = LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
            if (!alarmDate.isAfter(LocalDateTime.now())) {
                logger.warning("Напоминание в прошлое отправить нельзя!");
                telegramBot.execute(new SendMessage(chatId, "Напоминание в прошлое отправить нельзя!"));
                return;
            }
        } catch (DateTimeParseException exception) {
            telegramBot.execute(new SendMessage(chatId, "\"Добавление напоминания возможно только в следующем формате: 'дд.мм.гггг чч:мм' текст напоминания"));

        }

    }

    private void sendWelcomeMessage(Long chatId) {

        telegramBot.execute(new SendMessage(chatId,
                " Добро пожаловать в бот, который напомнит о важных событиях! "));
    }

    private void saveEntity(Long chatId, String notification, LocalDateTime alarmDate, LocalDateTime addedAt) {
        NotificationTask notificationTask = new NotificationTask(chatId, notification, alarmDate, addedAt);
        repository.save(notificationTask);
        logger.info("Напоминание " + notificationTask + " сохранено");
        telegramBot.execute(new SendMessage(chatId, "Напоминание " + notificationTask + " сохранено"));

    }


}
