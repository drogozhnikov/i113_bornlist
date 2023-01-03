package bornlist.service.converter;

import bornlist.dto.UserDto;
import bornlist.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<UserDto> convertListToDto(List<UserEntity> entitiesList){
        return entitiesList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto convertToDto(UserEntity inputEntity) {
        return modelMapper.map(inputEntity, UserDto.class);
    }

    public UserEntity convertToEntity(UserDto inputDTO) {
        return modelMapper.map(inputDTO, UserEntity.class);
    }
}
