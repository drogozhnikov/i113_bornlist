package bornlist.repository;

import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {

    List<UnitEntity> findAllByNotifyIsTrue();

    List<UnitEntity> findAllByUserEntity_UserName(String userEntity_userName);

    void deleteById(int id);
}
