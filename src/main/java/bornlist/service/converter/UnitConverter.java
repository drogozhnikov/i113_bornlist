package bornlist.service.converter;

import bornlist.dto.UnitDto;
import bornlist.entity.UnitEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UnitConverter {

    private ModelMapper modelMapper;

    public UnitConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<UnitDto> convertListToDto(List<UnitEntity> entitiesList){
        return entitiesList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UnitDto convertToDto(UnitEntity inputEntity) {
        return modelMapper.map(inputEntity, UnitDto.class);
    }

    public UnitEntity convertToEntity(UnitDto inputDTO) {
        return modelMapper.map(inputDTO, UnitEntity.class);
    }
}
