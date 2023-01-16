package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.database.repo.TaskRepository;
import root.timemanagementapp.dto.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SprintService sprintService;
    @Autowired
    private UserService userService;

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task addNoteToTask(Long taskId, String note) throws Exception {
        Task task = findTaskById(taskId);
        task.setNote(note);
        return save(task);
    }

    public Task findTaskById(Long taskId) throws Exception {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new Exception("Not found task by id=" + taskId);
        }
    }

    public Task setElapsedTime(TaskDto taskDto) throws Exception {
        Task task = findTaskById(taskDto.getId());
        task.setElapsedTimeInSeconds(taskDto.getElapsedTimeInSeconds());
        return save(task);
    }

    public Task completeTask(TaskDto taskDto) throws Exception {
        Task task = findTaskById(taskDto.getId());
        task.setCompleted(!task.getCompleted());
        return save(task);
    }

    public Task changePriority(TaskDto taskDto) throws Exception {
        Task task = findTaskById(taskDto.getId());
        task.setHighPriority(!task.getHighPriority());
        return save(task);
    }

    public Boolean deleteTask(TaskDto taskDto) {
        try {
            Task task = findTaskById(taskDto.getId());
            taskRepository.delete(task);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Task addTask(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setHighPriority(taskDto.getHighPriority());
        task.setCompleted(false);
        task.setEstimatedTimeInSeconds(taskDto.getEstimatedTimeInSeconds());
        task.setElapsedTimeInSeconds(0L);
        task.setSprint(sprintService.getActiveSprint());
        task.setUser(userService.findUserById(taskDto.getUserId()));
        return taskRepository.save(task);
    }

    public List<TaskDto> getActiveSprintCompletedTasks() {
        return sprintService.getActiveSprint().getTasks().stream().map(TaskDto::new).filter(TaskDto::isCompleted).collect(Collectors.toList());
    }

    public List<TaskDto> getActiveSprintOpenedTasks() {
        return sprintService.getActiveSprint().getTasks().stream().map(TaskDto::new).filter(TaskDto -> !TaskDto.isCompleted()).collect(Collectors.toList());
    }

    public List<TaskDto> getActiveSprintCompletedTasksByUserId(Long userId) {
        return getActiveSprintCompletedTasks().stream().filter(TaskDto -> TaskDto.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public List<TaskDto> getActiveSprintOpenedTasksByUserId(Long userId) {
        return getActiveSprintOpenedTasks().stream().filter(TaskDto -> TaskDto.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public List<TaskDto> getActiveSprintHighPriorityCompletedTasksByUserId(Long userId){
        return getActiveSprintCompletedTasksByUserId(userId).stream().filter(TaskDto::isHighPriority).collect(Collectors.toList());
    }

    public List<TaskDto> getActiveSprintHighPriorityOpenedTasksByUserId(Long userId){
        return getActiveSprintOpenedTasksByUserId(userId).stream().filter(TaskDto::isHighPriority).collect(Collectors.toList());
    }

    public List<TaskDto> getActiveSprintLowPriorityCompletedTasksByUserId(Long userId){
        return getActiveSprintCompletedTasksByUserId(userId).stream().filter(TaskDto -> !TaskDto.isHighPriority()).collect(Collectors.toList());
    }

    public List<TaskDto> getActiveSprintLowPriorityOpenedTasksByUserId(Long userId){
        return getActiveSprintOpenedTasksByUserId(userId).stream().filter(TaskDto -> !TaskDto.isHighPriority()).collect(Collectors.toList());
    }

}
