package app.PP_311.controller;

import app.PP_311.model.User;
import app.PP_311.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //Страница со всеми пользователями
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    //Форма создания пользователя
    @GetMapping("/save")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "save";
    }

    //Отправка формы
    @PostMapping()
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "save";
        }
        userService.save(user);
        return "redirect:/users/";
    }
}
