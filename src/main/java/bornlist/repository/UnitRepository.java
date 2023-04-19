package bornlist.repository;

import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {

//    List<UnitEntity> findAllByUserIdOrderByDate(int userId);
    List<UnitEntity> findAllByNotifyIsTrue();

    List<UnitEntity> findAll();

    void deleteById(int id);
}
