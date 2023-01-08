package bornlist.service.telegram;

import bornlist.entity.UserEntity;
import bornlist.service.MessageService;
import bornlist.service.UnitService;
import bornlist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TelegramService {

    private MessageService messageService;
    private UnitService unitService;
    private UserService userService;

    private final String RESPONSE_USER_NOT_EXIST = "telegram.response.usernotexist.start";
    private final String RESPONSE_USER_CREATED = "telegram.response.usernotexist.end";
    private final String MISUDERDTAND = "telegram.response.misunderstanding";

    private final String COMMAND_NEW_USER = "telegram.request.command.newuser";
    private final String COMMAND_ADD_UNIT = "telegram.request.command.add";
    private final String COMMAND_INFO = "telegram.request.command.info";
    private final String COMMAND_GET = "telegram.request.command.get";
    private final String COMMAND_GET_ALL = "telegram.request.command.getAll";

    public String getResponce(String chatId, String inputMessage) {
        if(checkUser(chatId)){
            parseAndAct(chatId, inputMessage);
        }else{
            return messageService.getMessage(RESPONSE_USER_NOT_EXIST);
        }
        return parseAndAct(chatId, inputMessage);
    }

    public String parseAndAct(String chatId, String textMsg) {
        if(textMsg.startsWith(messageService.getMessage(RESPONSE_USER_NOT_EXIST))){
            createUser(textMsg, chatId);
            return messageService.getMessage(RESPONSE_USER_CREATED);
        }if(textMsg.contains(COMMAND_ADD_UNIT)) {
            return "unit_creation_api";
        }if(textMsg.contains(COMMAND_INFO)) {
            return "unit_creation_info";
        }if(textMsg.contains(COMMAND_GET)) {
            return "unit_creation_get";
        }if(textMsg.contains(COMMAND_GET_ALL)) {
            return "unit_creation_getAll";
        }
        else{
            return messageService.getMessage(MISUDERDTAND);
        }
    }

    private void createUser(String textMsg, String chatId){
        String selectedUserName = textMsg.replace(
                messageService.getMessage(COMMAND_NEW_USER),
                "");
        userService.create(new UserEntity(chatId, selectedUserName));
    }

    private boolean checkUser(String chatId){
        Optional<UserEntity> optionalTest = Optional.ofNullable(userService.findUserByChatId(chatId));
        return optionalTest.isPresent();
    }

}
