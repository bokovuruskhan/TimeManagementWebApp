package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.dto.NoteDto;
import root.timemanagementapp.dto.TaskDto;
import root.timemanagementapp.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDto> read() {
        return taskService.getAllTasks();
    }

    @PostMapping("/note")
    public Task addNote(@RequestBody NoteDto noteDto) throws Exception {
        return taskService.addNoteToTask(noteDto.getTaskId(), noteDto.getNote());
    }

    @PostMapping("/priority/change")
    public Task changePriority(@RequestBody TaskDto taskDto) throws Exception {
        return taskService.changePriority(taskDto);
    }

    @PostMapping("/time/elapsed")
    public Task setElapsedTime(@RequestBody TaskDto taskDto) throws Exception {
        return taskService.setElapsedTime(taskDto);
    }

    @PostMapping("/complete")
    public Task completeTask(@RequestBody TaskDto taskDto) throws Exception {
        return taskService.completeTask(taskDto);
    }

    @DeleteMapping
    public Boolean delete(@RequestBody TaskDto taskDto) {
        return taskService.deleteTask(taskDto);
    }

    @PostMapping
    public Task create(@RequestBody TaskDto taskDto){
        return taskService.addTask(taskDto);
    }
}
