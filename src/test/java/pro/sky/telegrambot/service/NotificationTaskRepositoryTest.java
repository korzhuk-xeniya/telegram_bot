package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@Repository
public interface NotificationTaskRepositoryTest
        extends JpaRepository<NotificationTask, Long> {
}

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {
    @Mock
    NotificationTaskRepository repository;
    @Mock
    NotificationTaskRepositoryTest repositoryTest;
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
//        notificationService.sendMessage(message.getChatId(), message.getNotification());
//        Mockito.verify(notificationService, atLeastOnce())
//                .sendMessage(message.getChatId(), message.getNotification());

    }

    @Test
    void dateFormatterValidation(Long chatId, Matcher matcher) {

    }
}