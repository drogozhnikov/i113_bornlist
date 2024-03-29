package bornlist.repository;

import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {

    List<UnitEntity> findAllByNotifyIsTrue();

    List<UnitEntity> findAllByUserEntity(UserEntity userEntity);

    void deleteAllByUserEntity(UserEntity userEntity);

    void deleteById(int id);
}
