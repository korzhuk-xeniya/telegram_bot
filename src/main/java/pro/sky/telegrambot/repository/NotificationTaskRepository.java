package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.NotificationTask;

import java.util.Optional;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
    Optional<NotificationTask> saveById(Long Id);

}
