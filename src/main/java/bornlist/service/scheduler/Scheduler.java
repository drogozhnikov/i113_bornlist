package bornlist.service.scheduler;

import bornlist.service.MessageService;
import bornlist.service.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Scheduler {

    private final UnitService unitService;
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

//    @Scheduled(fixedRate = 10000)
//    public void testSheduler(){
////        action();
//        String chatId = "425222583";
//        UserEntity user = userService.findUserByChatId(chatId);
//        List<UnitEntity> entities = unitService.findByUserId(user.getId());
//        System.out.println(entities);
//    }

    private void action() {

    }

//    private TelegramUnit prepareUnit(String chatId){
//        return new TelegramUnit().builder()
//                .userName("test")
//                .chatId(chatId)
//                .build();
//    }
}
