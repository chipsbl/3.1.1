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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/users")
public class UserEditorController {

    private final UserService userService;

    public UserEditorController(UserService userService) {
        this.userService = userService;
    }

    //Обновление пользователя
    @GetMapping("/update")
    public String editUser(@RequestParam int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update";
    }

    //Отправка формы обновления
    @PostMapping("/update")
    public String updateUser(@RequestParam int id, @Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            user.setId(id);
            return "update";
        }
        userService.update(user, id);
        return "redirect:/users/";
    }

    //Окно всплытия удаление пользователя
    @GetMapping("/delete")
    public String deleteUserWindow(@RequestParam int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "delete";
    }

    //Удаление пользователя
    @PostMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        userService.delete(id);
        return "redirect:/users/";
    }
}
