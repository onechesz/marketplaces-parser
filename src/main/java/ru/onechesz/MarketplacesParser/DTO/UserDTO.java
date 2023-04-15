package ru.onechesz.MarketplacesParser.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.onechesz.MarketplacesParser.Entities.UserEntity;

@ToString
public class UserDTO {
    @Getter
    @Setter
    @Size(min = 4, max = 32, message = "should be from 4 to 32 characters long.")
    @NotNull(message = "should exist.")
    private String username;
    @Getter
    @Setter
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "should be correct.")
    @NotNull(message = "should exist.")
    private String email;
    @Getter
    @Setter
    @Size(min = 5, max = 32, message = "should be from 5 to 32 characters long.")
    @NotNull(message = "should exist.")
    private String password;
    @Getter
    @Setter
    @Size(min = 5, max = 32, message = "should be from 5 to 32 characters long.")
    @NotNull(message = "should exist.")
    private String password2;
    @Getter
    @Setter
    @Pattern(regexp = "^ROLE_(ADMIN|USER)", message = "should be correct.")
    @NotNull(message = "should exist.")
    private String role;

    public UserEntity convertToUserEntity() {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setRole(role);

        return userEntity;
    }
}
