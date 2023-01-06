package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import root.timemanagementapp.service.SprintService;
import root.timemanagementapp.service.TaskService;

@Controller
public class WebController {
    @Autowired
    private SprintService sprintService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model) throws Exception {
        model.addAttribute("lowPriorityCompletedTasks", taskService.getActiveSprintLowPriorityCompletedTasks());
        model.addAttribute("highPriorityCompletedTasks", taskService.getActiveSprintHighPriorityCompletedTasks());
        model.addAttribute("lowPriorityOpenedTasks", taskService.getActiveSprintLowPriorityOpenedTasks());
        model.addAttribute("highPriorityOpenedTasks", taskService.getActiveSprintHighPriorityOpenedTasks());
        model.addAttribute("activeSprint", sprintService.getActiveSprint());
        return "index";
    }

}
