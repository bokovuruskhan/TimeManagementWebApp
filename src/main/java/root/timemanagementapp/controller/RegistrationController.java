package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.timemanagementapp.dto.RoleNames;
import root.timemanagementapp.dto.UserDto;
import root.timemanagementapp.service.RoleService;
import root.timemanagementapp.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String showRegistrationPage(Model model) {
        roleService.createRolesIfNotExists();
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", RoleNames.getRoleNamesCollection());
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("user") UserDto userDto, Errors errors, Model model) throws Exception {
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.save(userDto)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/login";
    }


}
