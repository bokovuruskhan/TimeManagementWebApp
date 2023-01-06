package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.database.repo.TaskRepository;

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

    public List<Task> getActiveSprintCompletedTasks() throws Exception {
        return sprintService.getActiveSprint().getTasks().stream().filter(Task::isCompleted).collect(Collectors.toList());
    }

    public List<Task> getActiveSprintOpenedTasks() throws Exception {
        return sprintService.getActiveSprint().getTasks().stream().filter(Task-> !Task.isCompleted()).collect(Collectors.toList());
    }

    public List<Task> getCurrentSprintHighPriorityCompletedTasks() throws Exception {
        return getActiveSprintCompletedTasks().stream().filter(Task::isHighPriority).collect(Collectors.toList());
    }

    public List<Task> getCurrentSprintHighPriorityOpenedTasks() throws Exception {
        return getActiveSprintOpenedTasks().stream().filter(Task::isHighPriority).collect(Collectors.toList());
    }

    public List<Task> getCurrentSprintLowPriorityCompletedTasks() throws Exception {
        return getActiveSprintCompletedTasks().stream().filter(Task->!Task.isHighPriority()).collect(Collectors.toList());
    }

    public List<Task> getCurrentSprintLowPriorityOpenedTasks() throws Exception {
        return getActiveSprintOpenedTasks().stream().filter(Task->!Task.isHighPriority()).collect(Collectors.toList());
    }

}
