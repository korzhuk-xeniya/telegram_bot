package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
//@RequiredArgsConstructor


public  class NotificationServiseImpl implements NotificationServise {

    private TelegramBot telegramBot;
    private NotificationTaskRepository repository;
    private Logger logger =  LoggerFactory.getLogger(NotificationServiseImpl.class);
    private static Pattern MESSAGE_PATTERN = Pattern.compile("([0-9\\.:\\s]{16})(\\s)(.+)");
    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");

    public NotificationServiseImpl(TelegramBot telegramBot, NotificationTaskRepository repository, Logger logger) {
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public void process(Update update) {


        if (update.message() == null) {
            logger.info("пользователь отправил пустое сообщение");
            return;
        }
        Long chatId = update.message().chat().id();
        String message = update.message().text();
        if (message == null) {
            sendMessage(chatId, "для начала работы, отправь /start");
            return;
        }
        if (message.equals("/start")) {
            sendWelcomeMessage(chatId);
            return;
        }
        Matcher matcher = MESSAGE_PATTERN.matcher(message);
        if (matcher.find()) {
            dateFormatterValidation(chatId, matcher);
            LocalDateTime alarmDate = LocalDateTime.parse(matcher.group(1), DATE_TIME_FORMATTER);
            String notification = matcher.group(3);
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            saveEntity(chatId, notification, alarmDate, now);
        } else {
            sendMessage(chatId,
                    "Добавление напоминания возможно только в следующем формате: 'дд.мм.гггг чч:мм' текст напоминания");
            return;
        }


    }

    private void dateFormatterValidation(Long chatId, Matcher matcher) {//нужен тест
        String dateString = matcher.group(1);
        try {
            LocalDateTime alarmDate = LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
            if (!alarmDate.isAfter(LocalDateTime.now())) {
                logger.warn("Напоминание в прошлое отправить нельзя!");
                sendMessage(chatId, "Напоминание в прошлое отправить нельзя!");
                return;
            }
        } catch (DateTimeParseException exception) {
            sendMessage(chatId, "Добавление напоминания возможно только в следующем формате: 'дд.мм.гггг чч:мм' текст напоминания");

        }

    }

    private void sendWelcomeMessage(Long chatId) {
        sendMessage(chatId," Добро пожаловать в бот, который напомнит о важных событиях! ");
    }

    private void saveEntity(Long chatId, String notification, LocalDateTime alarmDate, LocalDateTime addedAt) {
        NotificationTask notificationTask = new NotificationTask(chatId, notification, alarmDate, addedAt);
        repository.save(notificationTask);
        logger.info("Напоминание " + notificationTask + " сохранено");
        sendMessage(chatId, "Напоминание " + notificationTask + " сохранено");

    }
    @Override
    public void sendMessage(Long chatId, String messageText) {
        SendMessage sendMessage = new SendMessage(chatId,messageText);
        telegramBot.execute(sendMessage);
    }
    @Override
    @Scheduled(cron = "0 0/1 * * * *")
    public void recordsToDataBase() {
        List<NotificationTask> records = repository
                .findByAlarmDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        records.forEach(record -> {
            logger.info("Напоминание было отправлено");
            sendMessage(record.getChatId(), String.format("Не забудь: " + record.getNotification(), record.getAlarmDate()));
        });

    }




}
