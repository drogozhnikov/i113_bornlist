package bornlist.service;

import bornlist.entity.UnitEntity;
import bornlist.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnitService {

    private UnitRepository unitRepository;
    private final MessageService messageService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void create(UnitEntity unitEntity) {
        unitRepository.save(unitEntity);
    }

    public void createAll(List<UnitEntity> unitEntities) {
        for(UnitEntity entity: unitEntities)
        unitRepository.save(entity);
    }

    public List<UnitEntity> findAll() {
        return unitRepository.findAll();
    }

    public UnitEntity findById(int id) {
        return unitRepository.findById(id);
    }

    public List<UnitEntity> findByUserId(int userId){
        return unitRepository.findAllByUserId(userId);
    }

    public boolean update(UnitEntity unitEntity, int id) {
        if(unitRepository.existsById(id)){
            unitEntity.setId(id);
            unitRepository.save(unitEntity);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if(unitRepository.existsById(id)){
            unitRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<UnitEntity> getSortedUnitsById(int userId){
        return unitRepository.findAllByUserIdOrderByDate(userId);
    }


}
