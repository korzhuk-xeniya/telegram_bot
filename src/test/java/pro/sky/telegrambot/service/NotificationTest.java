package pro.sky.telegrambot.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationTest {
    @Autowired
    private NotificationTaskRepository notificationTaskRepository;

    @Test
     void saveEntity() {
        LocalDateTime ldt = LocalDateTime.of(2024, 1, 21, 20, 0, 0, 0);
        LocalDateTime ldt2 = LocalDateTime.of(2024, 1, 24, 20, 0, 0, 0);
        NotificationTask message = new NotificationTask(1229918645L, "Отправить напоминание", ldt2, ldt);

        notificationTaskRepository.save(message);
        assertNotNull(notificationTaskRepository.findAllById(Collections.singleton(message.getId())));
    }
}

