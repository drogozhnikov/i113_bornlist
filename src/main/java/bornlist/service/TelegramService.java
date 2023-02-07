package bornlist.service;

import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import bornlist.model.Command;
import bornlist.model.TelegramUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
                getNearestUnit(unit);
                break;
            }
            case HELP:{
                unit.setMessage(messageService.getMessage(COMMAND_HELP));
                break;
            }
        }
    }

    private void addUnit(TelegramUnit unit){
        if(messageService.isMatchesRegex(unit.getMessage())){
            unitService.create(messageService.fillEntity(unit.getMessage()));
            unit.setMessage(messageService.getMessage(TEMPLATE_SUCCESS));
        }else{
            unit.setMessage(messageService.getMessage(TEMPLATE_MISS));
        }
    }

    private void getNearestUnit(TelegramUnit tUnit){
        UserEntity user = userService.findUserByChatId(tUnit.getChatId());
        List<UnitEntity> units = unitService.getSortedUnitsById(user.getId());
            if(units.size()!=0){
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

    private boolean isUserExist(String chatId) {
        Optional<UserEntity> optionalTest = Optional.ofNullable(userService.findUserByChatId(chatId));
        return optionalTest.isPresent();
    }

}
