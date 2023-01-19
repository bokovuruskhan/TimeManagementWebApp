package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import root.timemanagementapp.database.model.User;
import root.timemanagementapp.dto.UserDto;
import root.timemanagementapp.service.SprintService;
import root.timemanagementapp.service.StatisticsService;
import root.timemanagementapp.service.TaskService;
import root.timemanagementapp.service.UserService;

@Controller
public class WebController {
    @Autowired
    private SprintService sprintService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/login")
    public String showLoginView(Model model) {
        return "login";
    }

    @RequestMapping("/dev")
    public String showDevView(@RequestParam("devId") Long devId,Model model) {
        User developer = userService.findUserById(devId);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("lowPriorityCompletedTasks", taskService.getActiveSprintLowPriorityCompletedTasksByUserId(developer.getId()));
        model.addAttribute("highPriorityCompletedTasks", taskService.getActiveSprintHighPriorityCompletedTasksByUserId(developer.getId()));
        model.addAttribute("lowPriorityOpenedTasks", taskService.getActiveSprintLowPriorityOpenedTasksByUserId(developer.getId()));
        model.addAttribute("highPriorityOpenedTasks", taskService.getActiveSprintHighPriorityOpenedTasksByUserId(developer.getId()));
        model.addAttribute("activeSprint", sprintService.getActiveSprint());
        model.addAttribute("developers", userService.findAllDevelopers());
        model.addAttribute("statistics", statisticsService.getActiveSprintStatistics());
        UserDto currentUserDto = new UserDto();
        currentUserDto.setId(currentUser.getId());
        currentUserDto.setUsername(currentUser.getUsername());
        currentUserDto.setRole(currentUser.getAuthorities().toArray()[0].toString());
        model.addAttribute("currentUser", currentUserDto);
        model.addAttribute("developer", developer);
        return "index";
    }

    @RequestMapping("/")
    public String index(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("lowPriorityCompletedTasks", taskService.getActiveSprintLowPriorityCompletedTasksByUserId(currentUser.getId()));
        model.addAttribute("highPriorityCompletedTasks", taskService.getActiveSprintHighPriorityCompletedTasksByUserId(currentUser.getId()));
        model.addAttribute("lowPriorityOpenedTasks", taskService.getActiveSprintLowPriorityOpenedTasksByUserId(currentUser.getId()));
        model.addAttribute("highPriorityOpenedTasks", taskService.getActiveSprintHighPriorityOpenedTasksByUserId(currentUser.getId()));
        model.addAttribute("activeSprint", sprintService.getActiveSprint());
        model.addAttribute("developers", userService.findAllDevelopers());
        model.addAttribute("statistics", statisticsService.getActiveSprintStatistics());
        UserDto currentUserDto = new UserDto();
        currentUserDto.setId(currentUser.getId());
        currentUserDto.setUsername(currentUser.getUsername());
        currentUserDto.setRole(currentUser.getAuthorities().toArray()[0].toString());
        model.addAttribute("currentUser", currentUserDto);
        return "index";
    }

}
