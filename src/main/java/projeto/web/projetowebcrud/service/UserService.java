package projeto.web.projetowebcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.web.projetowebcrud.controller.CreateUserDto;
import projeto.web.projetowebcrud.entity.User;
import projeto.web.projetowebcrud.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        var entity = new User(UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);
        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserbyId(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }
}
