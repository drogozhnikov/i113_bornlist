package bornlist.service.scheduler;

import bornlist.dto.UnitDto;
import bornlist.entity.UserEntity;
import bornlist.service.BindService;
import bornlist.service.MessageService;
import bornlist.service.UserService;
import bornlist.service.telegram.TelegramBot;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Scheduler {

    private final TelegramBot telegramBot;
    private final BindService bindService;
    private final UserService userService;
    private final MessageService messageService;

    private final String REMINDER_START = "telegram.response.reminder.start";
    private final String REMINDER_END = "telegram.response.reminder.end";

    @Scheduled(cron = "${interval-in-cron-morning}")
    public void morningSheduler() {
        action();
    }

    @Scheduled(cron = "${interval-in-cron-evening}")
    public void eveningSheduler() {
        action();
    }

//    @Scheduled(fixedRate = 100000)
//    public void testSheduler(){
//        action();
//    }

    private void action() {
        List<UserEntity> userList = userService.readAll();
        StringBuilder response = new StringBuilder(messageService.getMessage(REMINDER_START));
        for (UserEntity user : userList) {
            response.append(messageService.prepareUnits(bindService.findToday(user.getTelegramId())));
            response.append(messageService.getMessage(REMINDER_END));
            telegramBot.sendMessage(user.getTelegramId(), response.toString());
        }
    }
}
