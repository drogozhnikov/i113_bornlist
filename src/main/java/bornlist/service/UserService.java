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

    public UserEntity findOrSaveAndGetMail(String mail) {
        if (Optional.ofNullable(mail).isPresent()) {
            return repository.findUserEntityByUserName(mail).orElseGet(() -> repository.save(new UserEntity(mail)));
        }
        throw new BlException("Please user name", HttpStatus.BAD_REQUEST);
    }
}
