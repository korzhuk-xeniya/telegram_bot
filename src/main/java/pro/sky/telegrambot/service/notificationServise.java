package pro.sky.telegrambot.service;

import org.hibernate.sql.Update;

public interface notificationServise {
    void  process(Update updates);
}
