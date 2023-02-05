package bornlist.repository;

import bornlist.entity.BindEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface BindRepository extends JpaRepository<BindEntity, Integer> {

    List<BindEntity> findAllByUserEntity_TelegramIdEqualsAndUnitEntity_DateEquals(String userEntity_telegramId, Date unitEntity_date);

}
