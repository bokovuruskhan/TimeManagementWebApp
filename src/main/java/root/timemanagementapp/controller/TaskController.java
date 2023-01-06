package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> read() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task create(@RequestBody Task task) throws Exception {
        return taskService.addTask(task);
    }
}
