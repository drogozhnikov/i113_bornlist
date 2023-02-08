package bornlist.service;

import bornlist.entity.UnitEntity;
import bornlist.model.Command;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Locale;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    private final String regexAdd = "(/+.{3,}\\W{1}.{3,100}\\W{1}.{3,100}\\W{1}\\d{4}[-]\\d{2}[-]\\d{2})";
    private final String regexUpdate = "(/+.{3,}\\W{1}\\d{1,3}\\W{1}.{3,100}\\W{1}.{3,100}\\W{1}\\d{4}[-]\\d{2}[-]\\d{2})";
    private final String regexDelete = "(/+.{3,}\\W{1}\\d{1,3})";

    private final String COMMAND_ADD = "telegram.request.command.add";
    private final String COMMAND_GET = "telegram.request.command.get";
    private final String COMMAND_UPDATE = "telegram.request.command.update";
    private final String COMMAND_DELETE = "telegram.request.command.delete";
    private final String COMMAND_LIST = "telegram.request.command.list";
    private final String COMMAND_HELP = "telegram.request.command.help";

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public Command parseCommand(String input){
        if(input!=null && input.contains("/")){
            if(input.contains(getMessage(COMMAND_ADD))){
                return Command.ADD;
            }
            if(input.contains(getMessage(COMMAND_GET))){
                return Command.GET;
            }
            if(input.contains(getMessage(COMMAND_LIST))){
                return Command.LIST;
            }
            if(input.contains(getMessage(COMMAND_UPDATE))){
                return Command.UPDATE;
            }
            if(input.contains(getMessage(COMMAND_DELETE))){
                return Command.DELETE;
            }
            if(input.contains(getMessage(COMMAND_HELP))){
                return Command.HELPFULL;
            }
        }
        return Command.HELP;
    }

    public boolean isMatchesRegexAdd(String input){
        if(input.matches(regexAdd)){
            return true;
        }
        return false;
    }

    public boolean isMatchesRegexUpdate(String input){
        if(input.matches(regexUpdate)){
            return true;
        }
        return false;
    }

    public boolean isMatchesRegexDelete(String input){
        if(input.matches(regexDelete)){
            return true;
        }
        return false;
    }

    public UnitEntity fillAddEntity(String input){
        String mass[] = input.split("\\.");
        UnitEntity resultUnit = new UnitEntity().builder()
                .firstName(mass[1])
                .lastName(mass[2])
                .build();
        DateTime datetime = DateTimeFormat
                .forPattern("yyyy-MM-dd")
                .parseDateTime(mass[3]);
        resultUnit.setDate(new Date(datetime.getMillis()));
        return resultUnit;
    }

    public void fillUpdateEntity(UnitEntity entity, String input){
        String mass[] = input.split("\\.");
            entity.setFirstName(mass[2]);
            entity.setLastName(mass[3]);
        DateTime datetime = DateTimeFormat
                .forPattern("yyyy-MM-dd")
                .parseDateTime(mass[4]);
            entity.setDate(new Date(datetime.getMillis()));
    }

    public String unitToString(UnitEntity unit){
        StringBuilder result = new StringBuilder();
            result.append("\n");
            result.append(unit.getId()).append(" ");
            result.append(unit.getFirstName()).append(" ");
            result.append(unit.getLastName()).append(" ");
            result.append(unit.getDate()).append(" ");
        return result.toString();
    }

}
