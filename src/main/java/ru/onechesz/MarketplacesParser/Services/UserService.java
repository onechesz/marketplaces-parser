package ru.onechesz.MarketplacesParser.Services;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.onechesz.MarketplacesParser.DTO.UserDTO;
import ru.onechesz.MarketplacesParser.Entities.UserEntity;
import ru.onechesz.MarketplacesParser.Repositories.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void save(@NotNull UserDTO userDTO) {
        UserEntity userEntity = userDTO.convertToUserEntity();
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());

        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
    }
}
