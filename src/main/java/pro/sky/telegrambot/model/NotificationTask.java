package pro.sky.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notification_tasks")


public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "notification")
    private String notification;
    @Column(name = "alarm_date")
    private LocalDateTime alarmDate;
    @Column(name = "added_at")
    private LocalDateTime addedAt;
    public NotificationTask(Long chatId, String notification, LocalDateTime alarmDate, LocalDateTime addedAt) {
        this.chatId = chatId;
        this.notification = notification;
        this.alarmDate = alarmDate;
        this.addedAt = addedAt;
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", notification='" + notification + '\'' +
                ", alarmDate=" + alarmDate +
                ", addedAt=" + addedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId)
                && Objects.equals(notification, that.notification)
                && Objects.equals(alarmDate, that.alarmDate)
                && Objects.equals(addedAt, that.addedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, notification, alarmDate, addedAt);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
