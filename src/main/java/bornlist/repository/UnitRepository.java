package bornlist.repository;

import bornlist.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {

    List<UnitEntity> findByFirstName(String name);

    List<UnitEntity> findAll();

    UnitEntity findById(int id);

    void deleteById(int id);
}
