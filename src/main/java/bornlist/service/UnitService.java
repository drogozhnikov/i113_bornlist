package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.entity.UnitEntity;
import bornlist.exception.BlException;
import bornlist.repository.UnitRepository;
import bornlist.service.converter.UnitConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitService {

    private UnitRepository repository;
    private UnitConverter converter;

    public List<UnitDto> getAll() {
        return converter.convertEntitiesToDto(repository.findAll());
    }

    public void create(UnitDto unitDto) {
        repository.save(converter.convertDtoToEntity(unitDto));
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

//    public List<UnitDto> getSortedUnitsById(int userId){
//        return converter.convertEntitiesToDto(repository.findAllByUserIdOrderByDate(userId));
//    }


}
