package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;

import java.util.Collections;
import java.util.regex.Matcher;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;


@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {
    @Mock
    TelegramBot telegramBot;
    @Mock
    private NotificationTaskRepository notificationTaskRepository;
    @InjectMocks
    NotificationServiceImpl notificationService;
    LocalDateTime ldt = LocalDateTime.of(2024, 1, 21, 20, 0, 0, 0);
    LocalDateTime ldt2 = LocalDateTime.of(2024, 1, 22, 20, 0, 0, 0);
    private NotificationTask message = new NotificationTask(1229918645L, "Отправить напоминание", ldt2, ldt);

    @Test
    void process() {
    }

//    @Test
//    void testSaveEntity_modelOfMethodOfRepository() {
//
//        repositoryTest.save(message);
//        Assert.assertNotNull(repositoryTest.findOne(message))
//        NotificationTask savedMessage = notificationService.saveEntity(message.getChatId(),
//                message.getNotification(), message.getAlarmDate(),
//                message.getAddedAt());
//
//        assertEquals("John", savedUser.getName());
//    }
//
//    @Test
//    void saveEntity_shouldSaveNotificationInBd() {
//        notificationService.saveEntity(message.getChatId(), message.getNotification(),
//                message.getAlarmDate(), message.getAddedAt());
//
//        otificationService.sendMessage(message.getChatId(), message.getNotification()););
//        NotificationTask result = notificationService.saveEntity(message.getChatId(), message.getNotification(), message.getAlarmDate(),
//                message.getAddedAt());
//        when(repository.findByAlarmDate(message.getAlarmDate()).)
//
//        assertEquals(message, result);
//

//    }

    @Test
    void sendWelcomeMessage_checksThatMethodWasStarted() {
        notificationService.sendMessage(message.getChatId(), message.getNotification());
        Mockito.verify(telegramBot, atLeastOnce()).execute(any());


    }

//    @Test
//    void saveEntity() {
//        LocalDateTime ldt = LocalDateTime.of(2024, 1, 26, 20, 0, 0, 0);
//        LocalDateTime ldt2 = LocalDateTime.of(2024, 1, 27, 20, 0, 0, 0);
//        NotificationTask message = new NotificationTask(1229918645L, "Отправить напоминание", ldt2, ldt);
//        notificationService.saveEntity(message.getChatId(), message.getNotification(), message.getAlarmDate(), message.getAddedAt());
//        notificationTaskRepository.save(message);
//        Assertions.assertNotNull(notificationTaskRepository.findAllById(Collections.singleton(message.getId())));
//    }

    @Test
    void dateFormatterValidation(Long chatId, Matcher matcher) {

    }
}