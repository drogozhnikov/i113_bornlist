package bornlist.service.converter;

import bornlist.dto.UnitDto;
import bornlist.entity.UnitEntity;
import bornlist.service.UserService;
import bornlist.utils.DateCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UnitConverter {

    private UserService userService;
    private DateCalculator calculator;

    public List<UnitDto> convertEntitiesToDto(List<UnitEntity> entitiesList) {
        return entitiesList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<UnitEntity> convertDtoToEntities(List<UnitDto> dtoList) {
        return dtoList.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public UnitDto convertEntityToDto(UnitEntity inputEntity) {
        return UnitDto.builder()
                .id(inputEntity.getId())
                .userName(inputEntity.getUserEntity().getUserName())
                .firstName(inputEntity.getFirstName())
                .lastName(inputEntity.getLastName())
                .date(inputEntity.getDate())
                .daysLeft(calculator.countDaysBetweenToday(inputEntity.getDate()))
                .notify(inputEntity.isNotify())
                .description(inputEntity.getDescription())
                .build();
    }

    public UnitEntity convertDtoToEntity(UnitDto inputDTO) {
        return UnitEntity.builder()
                .userEntity(userService.findOrSaveAndGetMail(inputDTO.getUserName()))
                .firstName(inputDTO.getFirstName())
                .lastName(inputDTO.getLastName())
                .date(inputDTO.getDate())
                .notify(inputDTO.isNotify())
                .description(inputDTO.getDescription())
                .build();
    }

}
