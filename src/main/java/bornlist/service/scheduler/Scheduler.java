package bornlist.service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private int count = 0;

    @Scheduled(cron = "${interval-in-cron-morning}")
    public void morningSheduler(){
        System.out.println("showTime");
    }

    @Scheduled(cron = "${interval-in-cron-evening}")
    public void eveningSheduler(){
        System.out.println("sleepTime");
    }
}
