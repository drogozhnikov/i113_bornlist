package bornlist.service;

import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import bornlist.model.Command;
import bornlist.model.SortUnit;
import bornlist.model.TelegramUnit;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelegramService {

    private MessageService messageService;
    private UnitService unitService;
    private UserService userService;

    private final String TEMPLATE_MISS = "telegram.request.command.add.template.miss";
    private final String TEMPLATE_SUCCESS = "telegram.request.command.success";
    private final String TEMPLATE_EMPTY_LIST = "telegram.request.command.get.emptyList";
    private final String COMMAND_HELP = "telegram.request.command.help.helpText";
    private final String COMMAND_HELPFULL = "telegram.request.command.help.helpList";
    private final String COMMAND_LIST_EMPTY = "telegram.request.command.list.template.empty";
    private final String UNIT_NOT_EXIST = "telegram.request.command.notexist";

    private final int answerCount = 3;

    public void getResponce(TelegramUnit unit) {
        if (!isUserExist(unit.getChatId())) {
            createUser(unit);
        }
        doCommand(messageService.parseCommand(unit.getMessage()),unit);
    }

    private void createUser(TelegramUnit unit) {
        userService.create(
                new UserEntity().builder()
                        .userName(unit.getUserName())
                        .chatId(unit.getChatId())
                        .firstName(unit.getFirstName())
                        .lastName(unit.getLastName())
                        .build()
        );
    }

    private void doCommand(Command inputCommand, TelegramUnit unit){
        switch (inputCommand){
            case ADD:{
                addUnit(unit);
                break;
            }
            case GET:{
                setNearestUnits(unit);
                break;
            }
            case LIST:{
                setFullList(unit);
                break;
            }
            case DELETE:{
                delete(unit);
                break;
            }
            case UPDATE:{
                updateUnit(unit);
                break;
            }
            case HELPFULL:{
                unit.setMessage(messageService.getMessage(COMMAND_HELPFULL));
                break;
            }
            case HELP:{
                unit.setMessage(messageService.getMessage(COMMAND_HELP));
                break;
            }
        }
    }

    private void addUnit(TelegramUnit unit){
        if(messageService.isMatchesRegexAdd(unit.getMessage())){
            UserEntity user = userService.findUserByChatId(unit.getChatId());
            UnitEntity entity = messageService.fillAddEntity(unit.getMessage());
                entity.setUserId(user.getId());
            unitService.create(entity);
            unit.setMessage(messageService.getMessage(TEMPLATE_SUCCESS));
        }else{
            unit.setMessage(messageService.getMessage(TEMPLATE_MISS));
        }
    }

    private void updateUnit(TelegramUnit unit){
        if(messageService.isMatchesRegexUpdate(unit.getMessage())){
            int unitId = Integer.parseInt(unit.getMessage().split("\\.")[1]);
            if(isUnitExist(unitId)){
                UnitEntity entity = unitService.findById(unitId);
                messageService.fillUpdateEntity(entity, unit.getMessage());
                unitService.update(entity, entity.getId());
                unit.setMessage(messageService.getMessage(TEMPLATE_SUCCESS));
            }else{
                unit.setMessage(messageService.getMessage(UNIT_NOT_EXIST));
            }
        }else{
            unit.setMessage(messageService.getMessage(TEMPLATE_MISS));
        }
    }

    private void delete(TelegramUnit unit){
        if(messageService.isMatchesRegexDelete(unit.getMessage())){
            int unitId = Integer.parseInt(unit.getMessage().split("\\.")[1]);
            if(isUnitExist(unitId)){
                unitService.delete(unitId);
                unit.setMessage(messageService.getMessage(TEMPLATE_SUCCESS));
            }
            else{
                unit.setMessage(messageService.getMessage(UNIT_NOT_EXIST));
            }
        }else{
            unit.setMessage(messageService.getMessage(UNIT_NOT_EXIST));
        }
    }

    private void setFullList(TelegramUnit tUnit){
        UserEntity user = userService.findUserByChatId(tUnit.getChatId());
        List<UnitEntity> units = unitService.findByUserId(user.getId());
        if(!units.isEmpty()){
            for(UnitEntity entity:units){
                tUnit.setMessage(
                        tUnit.getMessage()
                        + messageService.unitToString(entity)
                );
            }
        }else{
            tUnit.setMessage(messageService.getMessage(COMMAND_LIST_EMPTY));
        }
    }

    private void setNearestUnits(TelegramUnit tUnit){
        UserEntity user = userService.findUserByChatId(tUnit.getChatId());
        List<UnitEntity> units = sortByDays(unitService.getSortedUnitsById(user.getId()));
            if(!units.isEmpty()){
                if(units.size()>answerCount){
                    for(int i=0;i<answerCount;i++){
                        tUnit.setMessage(tUnit.getMessage() + messageService.unitToString(units.get(i)));
                    }
                }else{
                    for(UnitEntity entity:units){
                        tUnit.setMessage(tUnit.getMessage() + messageService.unitToString(entity));
                    }
                }
            }else{
                tUnit.setMessage(messageService.getMessage(TEMPLATE_EMPTY_LIST));
            }
    }

    private ArrayList<UnitEntity> sortByDays(List<UnitEntity> inputList){
        ArrayList<SortUnit> sortUnitList= new ArrayList<>();
            for(UnitEntity entity:inputList){
                sortUnitList.add(
                        new SortUnit().builder()
                            .daysLeft(countDaysBetweenToday(entity.getDate()))
                            .entity(entity)
                            .build()
                );
            }
        Collections.sort(sortUnitList);
        ArrayList<UnitEntity> resultList = new ArrayList<>();
            for(SortUnit unit : sortUnitList){
                resultList.add(unit.getEntity());
            }
        return  resultList;
    }

    private int countDaysBetweenToday(Date inputDate){
        DateTime currentDate = new DateTime();
        DateTime birthDayDate = new DateTime(inputDate.getTime());

        DateTime yearsBetween = currentDate.minusYears(birthDayDate.getYear());
        DateTime birthdayOnCurrentYear = birthDayDate.plusYears(yearsBetween.getYear());
            if(birthdayOnCurrentYear.isBeforeNow()){
                birthdayOnCurrentYear = birthdayOnCurrentYear.plusYears(1);
            }
        Long days = birthdayOnCurrentYear.getMillis()-currentDate.getMillis();
        Long daysLeft = days/86400000;
        return daysLeft.intValue();
    }

    private boolean isUserExist(String chatId) {
        Optional<UserEntity> optionalTest = Optional.ofNullable(userService.findUserByChatId(chatId));
        return optionalTest.isPresent();
    }
    private boolean isUnitExist(int unitId) {
        Optional<UnitEntity> optionalTest = Optional.ofNullable(unitService.findById(unitId));
        return optionalTest.isPresent();
    }

}
