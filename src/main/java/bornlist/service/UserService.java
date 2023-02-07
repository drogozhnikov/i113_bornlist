package bornlist.service;

import bornlist.entity.UserEntity;
import bornlist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final MessageService messageService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void create(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(int id) {
        return userRepository.findById(id);
    }

    public boolean update(UserEntity userEntity, int id) {
        if (userRepository.existsById(id)) {
            userEntity.setId(id);
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    public UserEntity findUserByChatId(String chatId) {
        return userRepository.findUserByChatId(chatId);
    }

    public boolean delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
