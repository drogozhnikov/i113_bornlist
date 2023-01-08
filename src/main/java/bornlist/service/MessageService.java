package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.entity.UnitEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    private final String EMPTY_SPACE = " ";
    private final String NEW_LINE = "\n";

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public String prepareUnitName(UnitDto unit) {
        StringBuilder output = new StringBuilder();
        output.append(EMPTY_SPACE);
        output.append(unit.getLastName()).append(EMPTY_SPACE);
        output.append(unit.getFirstName()).append(EMPTY_SPACE);
        output.append(unit.getMiddleName()).append(EMPTY_SPACE);
        output.append(NEW_LINE);
        return output.toString();
    }

    public String prepareUnits(List<UnitDto> units) {
        StringBuilder output = new StringBuilder();
        output.append(NEW_LINE);
        for(UnitDto unit : units){
            output.append(EMPTY_SPACE);
            output.append(unit.getLastName()).append(EMPTY_SPACE);
            output.append(unit.getFirstName()).append(EMPTY_SPACE);
            output.append(unit.getMiddleName()).append(EMPTY_SPACE);
            output.append(NEW_LINE);
        }
        return output.toString();
    }

}
