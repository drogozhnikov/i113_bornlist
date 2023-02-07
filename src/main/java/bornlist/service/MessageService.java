package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import bornlist.model.Command;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    private final String regex = "(/+.{3,}\\W{1}.{3,100}\\W{1}.{3,100}\\W{1}\\d{4}[-]\\d{2}[-]\\d{2})";

    private final String COMMAND_ADD = "telegram.request.command.add";
    private final String COMMAND_GET = "telegram.request.command.get";

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
        }
        return Command.HELP;
    }

    public boolean isMatchesRegex(String input){
        if(input.matches(regex)){
            return true;
        }
        return false;
    }

    public UnitEntity fillEntity(String input){
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

    public String unitToString(UnitEntity unit){
        StringBuilder result = new StringBuilder();
            result.append("\n");
            result.append(unit.getFirstName()).append(" ");
            result.append(unit.getLastName()).append(" ");
            result.append(unit.getDate()).append(" ");
        return result.toString();
    }

}
