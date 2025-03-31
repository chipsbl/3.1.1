package app.PP_311.controller;

import app.PP_311.model.User;
import app.PP_311.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //Страница со всеми пользователями
    @Transactional(readOnly = true)
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    //Форма создания пользователя
    @Transactional(readOnly = true)
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

    //Обновление пользователя
    @Transactional(readOnly = true)
    @GetMapping("/update")
    public String editUser(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update";
    }

    //Отправка формы обновления
    @PostMapping("/update")
    public String updateUser(@RequestParam Long id, @Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            user.setId(id);
            return "update";
        }
        userService.update(user, id);
        return "redirect:/users/";
    }

    //Окно всплытия удаление пользователя
    @Transactional(readOnly = true)
    @GetMapping("/delete")
    public String deleteUserWindow(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "delete";
    }

    //Удаление пользователя
    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/users/";
    }
}