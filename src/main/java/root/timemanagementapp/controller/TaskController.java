package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.dto.NoteDto;
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

    @PostMapping("/note")
    public Task addNote(@RequestBody NoteDto noteDto) throws Exception {
        return taskService.addNoteToTask(noteDto.getTaskId(), noteDto.getNote());
    }

    @PostMapping("/priority/change")
    public Boolean changePriority(@RequestBody Task task) {
        return taskService.changePriority(task);
    }

    @DeleteMapping
    public Boolean delete(@RequestBody Task task) {
        return taskService.deleteTask(task);
    }

    @PostMapping
    public Task create(@RequestBody Task task) throws Exception {
        return taskService.addTask(task);
    }
}
