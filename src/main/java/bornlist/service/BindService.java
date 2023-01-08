package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.entity.BindEntity;
import bornlist.repository.BindRepository;
import bornlist.service.converter.BindConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BindService {

    private final BindRepository bindRepository;
    private final MessageService messageService;
    private BindConverter converter;

    public List<UnitDto> findToday(String telegramId) {
        List<BindEntity> userList = bindRepository
                .findAllByUserEntity_TelegramIdEqualsAndUnitEntity_DateEquals(
                        telegramId,
                        new Date(System.currentTimeMillis()
                        ));
        return converter.convertListToDto(userList);
    }


}
