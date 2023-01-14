package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.timemanagementapp.database.model.User;
import root.timemanagementapp.dto.RoleNames;
import root.timemanagementapp.dto.UserDto;
import root.timemanagementapp.service.SprintService;
import root.timemanagementapp.service.TaskService;

@Controller
public class WebController {
    @Autowired
    private SprintService sprintService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/login")
    public String showLoginView(Model model) {
        return "login";
    }

    @RequestMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().toArray()[0].toString().equals(RoleNames.MASTER_ROLE)) {
            return "redirect:/master";
        } else {
            return "redirect:/dev";
        }
    }

    @GetMapping("/dev")
    public String showDevView(Model model) throws Exception {
        model.addAttribute("lowPriorityCompletedTasks", taskService.getActiveSprintLowPriorityCompletedTasks());
        model.addAttribute("highPriorityCompletedTasks", taskService.getActiveSprintHighPriorityCompletedTasks());
        model.addAttribute("lowPriorityOpenedTasks", taskService.getActiveSprintLowPriorityOpenedTasks());
        model.addAttribute("highPriorityOpenedTasks", taskService.getActiveSprintHighPriorityOpenedTasks());
        model.addAttribute("activeSprint", sprintService.getActiveSprint());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = new UserDto();
        currentUser.setUsername(((User) auth.getPrincipal()).getUsername());
        currentUser.setRole(auth.getAuthorities().toArray()[0].toString());
        model.addAttribute("currentUser",currentUser);
        return "dev";
    }

    @GetMapping("/master")
    public String showMasterView(Model model) throws Exception {
        model.addAttribute("lowPriorityCompletedTasks", taskService.getActiveSprintLowPriorityCompletedTasks());
        model.addAttribute("highPriorityCompletedTasks", taskService.getActiveSprintHighPriorityCompletedTasks());
        model.addAttribute("lowPriorityOpenedTasks", taskService.getActiveSprintLowPriorityOpenedTasks());
        model.addAttribute("highPriorityOpenedTasks", taskService.getActiveSprintHighPriorityOpenedTasks());
        model.addAttribute("activeSprint", sprintService.getActiveSprint());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = new UserDto();
        currentUser.setUsername(((User) auth.getPrincipal()).getUsername());
        currentUser.setRole(auth.getAuthorities().toArray()[0].toString());
        model.addAttribute("currentUser",currentUser);
        return "master";
    }

}
