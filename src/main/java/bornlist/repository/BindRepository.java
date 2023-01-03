package bornlist.repository;

import bornlist.entity.BindEntity;
import bornlist.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface BindRepository extends JpaRepository<BindEntity, Integer> {


    BindEntity findBindEntityByUserEntityTelegramId(String telegramId);

    List<BindEntity> findDistinctByUserEntity_TelegramIdEqualsAndUnitEntity_DateLike(String userEntity_telegramId, Timestamp unitEntity_date);
    List<BindEntity> findAllByUserEntity_TelegramIdEqualsAndUnitEntity_DateEquals(String userEntity_telegramId, Date unitEntity_date);

    List<BindEntity> findDistinctByUserEntity_TelegramIdEquals(String userEntity_telegramId);
}
