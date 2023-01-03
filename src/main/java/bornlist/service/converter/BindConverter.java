package bornlist.service.converter;

import bornlist.dto.BindDto;
import bornlist.dto.UnitDto;
import bornlist.entity.BindEntity;
import bornlist.entity.UnitEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BindConverter {

    private ModelMapper modelMapper;

    public BindConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<UnitDto> convertListToDto(List<BindEntity> entitiesList){
        return getUnitsEntities(entitiesList).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ArrayList<UnitEntity> getUnitsEntities(List<BindEntity> entitiesList){
        ArrayList<UnitEntity> list = new ArrayList<>();
        for(BindEntity entity : entitiesList){
            list.add(entity.getUnitEntity());
        }
        return list;
    }

    public UnitDto convertToDto(UnitEntity inputEntity) {
        return modelMapper.map(inputEntity, UnitDto.class);
    }

    public UnitEntity convertToEntity(UnitDto inputDTO) {
        return modelMapper.map(inputDTO, UnitEntity.class);
    }
}
