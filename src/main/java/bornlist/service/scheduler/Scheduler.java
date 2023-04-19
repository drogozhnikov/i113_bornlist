package bornlist.service.scheduler;

import bornlist.dto.MessageDto;
import bornlist.dto.UnitDto;
import bornlist.entity.UserEntity;
import bornlist.service.MessageService;
import bornlist.service.UnitService;
import bornlist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class Scheduler {

    private final UnitService unitService;
    private final UserService userService;
    private final MessageService messageService;

    private final String REMINDER_START = "telegram.response.reminder.start";
    private final String REMINDER_END = "telegram.response.reminder.end";

//    @Scheduled(cron = "${interval-in-cron-morning}")
//    public void morningSheduler() {
//        action();
//    }

    @Scheduled(cron = "${interval-in-cron-evening}")
    public void eveningSheduler() {
        action();
    }

    @Scheduled(fixedRate = 10000)
    public void testSheduler() {
//        action();
    }

    private void action() {
        ArrayList<MessageDto> preparedUnits = prepareShedulerUnits();
        if (preparedUnits.size() > 0) {
            for (MessageDto messageDto : preparedUnits) {
                messageService.sendMessageToTelegram(messageDto);
            }
        }
    }

    public ArrayList<MessageDto> prepareShedulerUnits() {
        List<UnitDto> allActiveDto = unitService.findAllByNotifyIsTrue();
        List<UserEntity> userEntities = userService.getAll();
        if (allActiveDto.size() > 0) {
            List<UnitDto> allActiveTodayDto = filterByToday(allActiveDto);
            if (allActiveTodayDto.size() > 0) {
                HashMap<String, ArrayList<UnitDto>> resultMap = fillMap(userEntities, allActiveTodayDto);
                ArrayList<MessageDto> outputList = prepareUserMessages(resultMap);
                return outputList;
            }
        }
        return new ArrayList<>();
    }

    private List<UnitDto> filterByToday(List<UnitDto> input) {
        List<UnitDto> allActiveTodayDto = new ArrayList<>();
        for (UnitDto dto : input) {
            if (dto.getDaysLeft().equals("Today")) {
                allActiveTodayDto.add(dto);
            }
        }
        return allActiveTodayDto;
    }

    private HashMap<String, ArrayList<UnitDto>> fillMap(List<UserEntity> userEntities, List<UnitDto> allActiveTodayDto) {
        HashMap<String, ArrayList<UnitDto>> resultMap = new HashMap<>();
        for (UserEntity entity : userEntities) {
            String userName = entity.getUserName();
            ArrayList<UnitDto> units = new ArrayList<>();
            for (UnitDto unit : allActiveTodayDto) {
                if (unit.getUserName().equals(userName)) {
                    units.add(unit);
                }
            }
            resultMap.put(userName, units);
        }
        return resultMap;
    }

    private ArrayList<MessageDto> prepareUserMessages(HashMap<String, ArrayList<UnitDto>> inputMap) {
        ArrayList<MessageDto> resultList = new ArrayList<>();
        Set<String> keys = inputMap.keySet();
        for (String userName : keys) {
            StringBuilder message = prepareMessage(inputMap.get(userName));
            if (message.length() > 0) {
                resultList.add(MessageDto.builder()
                        .userName(userName)
                        .message(message.toString())
                        .build());
            }
        }
        return resultList;
    }

    private StringBuilder prepareMessage(ArrayList<UnitDto> units) {
        if (units.size() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Today birthday:" + "\n");
            for (UnitDto unit : units) {
                builder.append(unit.getFirstName()).append(" ").append(unit.getLastName()).append("\n");
            }
            return builder;
        }
        return new StringBuilder();
    }

}
