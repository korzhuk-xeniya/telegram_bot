package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import java.time.LocalDateTime;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {
    @Mock
    NotificationTaskRepository repository;
    TelegramBot telegramBot;
    @InjectMocks
    NotificationServiceImpl notificationService;
    LocalDateTime ldt = LocalDateTime.of(2024, 1, 21, 20, 0, 0, 0);
    LocalDateTime ldt2 = LocalDateTime.of(2024, 1, 22, 20, 0, 0, 0);
private NotificationTask message = new NotificationTask(1229918645L, "Отправить напоминание", ldt2, ldt);
    @Test
    void process() {
    }

//    @Test
//    void saveEntity_shouldSaveNotificationInBd() {
//        NotificationTask notificationTask = message;
//        when(repository.save(message)).thenReturn(notificationService.sendMessage(message.getChatId(),message.getNotification()););
//        NotificationTask result = notificationService.saveEntity(message.getChatId(), message.getNotification(),message.getAlarmDate(),
//                message.getAddedAt());
//        when(repository.findByAlarmDate(message.getAlarmDate()).)
//
//        assertEquals(message, result);
//
//
//
//    }
    @Test
    void sendWelcomeMessage_checksThatMethodWasStarted() {
        notificationService.sendMessage(message.getChatId(),message.getNotification());
        Mockito.verify(notificationService,atLeastOnce())
                .sendMessage(message.getChatId(),message.getNotification());

    }
    @Test
    void dateFormatterValidation(Long chatId, Matcher matcher){

    }
}