package ru.onechesz.MarketplacesParser.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.onechesz.MarketplacesParser.Entities.UserEntity;
import ru.onechesz.MarketplacesParser.Repositories.UserRepository;
import ru.onechesz.MarketplacesParser.Security.UserSecurity;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        if (userEntity.isEmpty()) throw new UsernameNotFoundException("There is no such user.");

        return new UserSecurity(userEntity.get());
    }
}
