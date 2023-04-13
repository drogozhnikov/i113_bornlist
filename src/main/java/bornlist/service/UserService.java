package bornlist.service;

import bornlist.entity.UserEntity;
import bornlist.exception.BlException;
import bornlist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public UserEntity findOrSaveAndGetMail(String userName) {
        if (Optional.ofNullable(userName).isPresent()) {
            return repository.findUserEntityByUserName(userName).orElseGet(() -> repository.save(new UserEntity(userName)));
        }
        throw new BlException("Please user name", HttpStatus.BAD_REQUEST);
    }
}
