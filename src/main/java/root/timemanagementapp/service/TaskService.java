package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.database.repo.TaskRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SprintService sprintService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) throws Exception {
        task.setCompleted(false);
        task.setElapsedTime(LocalTime.of(0,0));
        task.setSprint(sprintService.getActiveSprint());
        return taskRepository.save(task);
    }

    public List<Task> getActiveSprintCompletedTasks() throws Exception {
        return sprintService.getActiveSprint().getTasks().stream().filter(Task::isCompleted).collect(Collectors.toList());
    }

    public List<Task> getActiveSprintOpenedTasks() throws Exception {
        return sprintService.getActiveSprint().getTasks().stream().filter(Task -> !Task.isCompleted()).collect(Collectors.toList());
    }

    public List<Task> getActiveSprintHighPriorityCompletedTasks() throws Exception {
        return getActiveSprintCompletedTasks().stream().filter(Task::isHighPriority).collect(Collectors.toList());
    }

    public List<Task> getActiveSprintHighPriorityOpenedTasks() throws Exception {
        return getActiveSprintOpenedTasks().stream().filter(Task::isHighPriority).collect(Collectors.toList());
    }

    public List<Task> getActiveSprintLowPriorityCompletedTasks() throws Exception {
        return getActiveSprintCompletedTasks().stream().filter(Task -> !Task.isHighPriority()).collect(Collectors.toList());
    }

    public List<Task> getActiveSprintLowPriorityOpenedTasks() throws Exception {
        return getActiveSprintOpenedTasks().stream().filter(Task -> !Task.isHighPriority()).collect(Collectors.toList());
    }

}
