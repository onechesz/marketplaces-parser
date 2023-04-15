package ru.onechesz.MarketplacesParser.Controllers;

import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.onechesz.MarketplacesParser.DTO.UserDTO;
import ru.onechesz.MarketplacesParser.Services.UserService;
import ru.onechesz.MarketplacesParser.Validators.UserValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserValidator userValidator;
    private final UserService userService;

    public AuthController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new UserDTO());

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserDTO userDTO, @NotNull BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "auth/register";

        userValidator.validate(userDTO, bindingResult);

        if (bindingResult.hasErrors())
            return "auth/register";

        userService.save(userDTO);
        model.addAttribute("success", true);

        return "auth/register";
    }
}
