package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.NotificationServise;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@EnableScheduling
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private NotificationServise notificationServise;
    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(NotificationServise notificationServise, TelegramBot telegramBot) {
        this.notificationServise = notificationServise;
        this.telegramBot = telegramBot;
    }

//    @PostConstruct
//    public void init() {
//        telegramBot.setUpdatesListener(this);
//    }
//@Scheduled(cron = "0 0/1 ****")
//public void sendNotificationMessage() {
////        notificationServise.sch
//
//}
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", updates);
            notificationServise.process(update);
            // Process your updates here
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
