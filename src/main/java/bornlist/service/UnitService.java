package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import bornlist.exception.BlException;
import bornlist.repository.UnitRepository;
import bornlist.service.converter.UnitConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitService {

    private UnitRepository repository;
    private UnitConverter converter;
    private UserService userService;

    public List<UnitDto> getAll(String user) {
        UserEntity userEntity = userService.findOrSaveAndGetMail(user);
        List<UnitEntity> entitiesList = repository.findAllByUserEntity(userEntity);
        if (entitiesList.size() > 0) {
            return converter.convertEntitiesToDto(entitiesList);
        }
        return new ArrayList<>();
    }

    public UnitDto create(UnitDto unitDto) {
        UnitEntity entity = repository.save(converter.convertDtoToEntity(unitDto));
        return converter.convertEntityToDto(entity);
    }

    public UnitDto update(UnitDto unitDto) {
        Optional<UnitEntity> entity = repository.findById(unitDto.getId());
        if (entity.isPresent()) {
            UnitEntity updatedEntity = converter.convertDtoToEntity(unitDto);
            updatedEntity.setId(entity.get().getId());
            updatedEntity = repository.save(updatedEntity);
            return converter.convertEntityToDto(updatedEntity);
        }
        throw new BlException("Update error", HttpStatus.BAD_REQUEST);
    }

    public void delete(int id) {
        Optional<UnitEntity> entity = repository.findById(id);
        entity.ifPresent(mailEntity -> repository.delete(mailEntity));
    }

    public void deleteAll(String username) {
        UserEntity user = userService.findByUserName(username);
        List<UnitEntity> entityList = repository.findAllByUserEntity(user);
        if (entityList.size() > 0) {
            repository.deleteAll(entityList);
        }
    }

    public void loadJson(String userName, List<UnitDto> unitDtos) {
        for (UnitDto dto : unitDtos) {
            repository.save(converter.convertDtoToEntity(dto));
        }
    }

    public void replaceAll(String userName, List<UnitDto> unitDtos) {
        deleteAll(userName);
        for (UnitDto dto : unitDtos) {
            repository.save(converter.convertDtoToEntity(dto));
        }
    }

    public List<UnitDto> findAllByNotifyIsTrue() {
        List<UnitEntity> entityList = repository.findAllByNotifyIsTrue();
        if (entityList.size() > 0) {
            return converter.convertEntitiesToDto(entityList);
        }
        return new ArrayList<>();
    }

}
