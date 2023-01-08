package bornlist.repository;

import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByUserName(String name);

    UserEntity findUserByTelegramId(String chatId);

    List<UserEntity> findAll();

    UserEntity findById(int id);

    void deleteById(int id);
}
