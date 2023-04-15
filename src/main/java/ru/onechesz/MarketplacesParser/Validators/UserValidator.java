package ru.onechesz.MarketplacesParser.Validators;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.onechesz.MarketplacesParser.DTO.UserDTO;
import ru.onechesz.MarketplacesParser.Services.UserService;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        String username = userDTO.getUsername();
        String email = userDTO.getEmail();

        if (!userDTO.getPassword().equals(userDTO.getPassword2()))
            errors.rejectValue("password2", "", "not equals");

        if (userService.findByUsername(username).isPresent())
            errors.rejectValue("username", "", "already taken");

        if (userService.findByEmail(email).isPresent())
            errors.rejectValue("email", "", "already taken");
    }
}
