package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    @Id
    @GeneratedValue
    private Long id;
    private Long chatId;
    private String notification,
    private LocalDateTime alarmDate;
    private LocalDateTime addedAt;
    public NotificationTask(Long id, Long chatId, String notification, LocalDateTime alarmDate, LocalDateTime addedAt) {
        this.id = id;
        this.chatId = chatId;
        this.notification = notification;
        this.alarmDate = alarmDate;
        this.addedAt = addedAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && Objects.equals(notification, that.notification) && Objects.equals(alarmDate, that.alarmDate) && Objects.equals(addedAt, that.addedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, notification, alarmDate, addedAt);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public LocalDateTime getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(LocalDateTime alarmDate) {
        this.alarmDate = alarmDate;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }


}
