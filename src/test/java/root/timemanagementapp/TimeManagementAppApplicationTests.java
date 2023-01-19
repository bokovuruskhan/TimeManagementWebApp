package root.timemanagementapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.database.model.User;
import root.timemanagementapp.dto.RoleNames;
import root.timemanagementapp.dto.TaskDto;
import root.timemanagementapp.dto.UserDto;
import root.timemanagementapp.service.TaskService;
import root.timemanagementapp.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeManagementAppApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Test
    void contextLoads() {
    }

    @Test
    public void addUserTest() throws Exception {

        final var username = "TEST_USERNAME";

        assertNull(userService.findUserByUsername(username));
        UserDto userDto = new UserDto();
        userDto.setPassword("password");
        userDto.setRole(RoleNames.DEV_ROLE);
        userDto.setUsername(username);
        userService.save(userDto);

        User user = userService.findUserByUsername(username);
        assertNotEquals(user, null);
        userService.delete(user.getId());
    }

    @Test
    public void addTaskTest() throws Exception {

        final var username = "TEST_USERNAME";

        UserDto userDto = new UserDto();
        userDto.setPassword("password");
        userDto.setRole(RoleNames.DEV_ROLE);
        userDto.setUsername(username);
        userService.save(userDto);

        User user = userService.findUserByUsername(username);
        assertNotEquals(user, null);


        TaskDto taskDto = new TaskDto();
        taskDto.setName("test");
        taskDto.setEstimatedHours(0L);
        taskDto.setEstimatedMinutes(0L);
        taskDto.setElapsedSeconds(0L);
        taskDto.setHighPriority(false);
        taskDto.setUserId(user.getId());
        Task task = taskService.addTask(taskDto);

        assertNotEquals(taskService.findTaskById(task.getId()), null);

        taskService.deleteTask(taskDto);
        userService.delete(user.getId());
    }

    @Test
    public void changeTaskPriorityTest() throws Exception {

        final var username = "TEST_USERNAME";

        UserDto userDto = new UserDto();
        userDto.setPassword("password");
        userDto.setRole(RoleNames.DEV_ROLE);
        userDto.setUsername(username);
        userService.save(userDto);

        User user = userService.findUserByUsername(username);
        assertNotEquals(user, null);


        TaskDto taskDto = new TaskDto();
        taskDto.setName("test");
        taskDto.setEstimatedHours(0L);
        taskDto.setEstimatedMinutes(0L);
        taskDto.setElapsedSeconds(0L);
        taskDto.setHighPriority(false);
        taskDto.setUserId(user.getId());
        Task task = taskService.addTask(taskDto);

        assertNotEquals(taskService.findTaskById(task.getId()), null);

        task = taskService.findTaskById(task.getId());
        assertEquals(task.getHighPriority(),false);
        task.setHighPriority(true);
        taskService.save(task);
        task = taskService.findTaskById(task.getId());
        assertEquals(task.getHighPriority(),true);

        taskService.deleteTask(taskDto);
        userService.delete(user.getId());
    }

    @Test
    public void completeTaskPriorityTest() throws Exception {

        final var username = "TEST_USERNAME";

        UserDto userDto = new UserDto();
        userDto.setPassword("password");
        userDto.setRole(RoleNames.DEV_ROLE);
        userDto.setUsername(username);
        userService.save(userDto);

        User user = userService.findUserByUsername(username);
        assertNotEquals(user, null);


        TaskDto taskDto = new TaskDto();
        taskDto.setName("test");
        taskDto.setEstimatedHours(0L);
        taskDto.setEstimatedMinutes(0L);
        taskDto.setElapsedSeconds(0L);
        taskDto.setHighPriority(false);
        taskDto.setUserId(user.getId());
        Task task = taskService.addTask(taskDto);

        assertNotEquals(taskService.findTaskById(task.getId()), null);

        task = taskService.findTaskById(task.getId());
        assertEquals(task.getCompleted(),false);
        task.setCompleted(true);
        taskService.save(task);
        task = taskService.findTaskById(task.getId());
        assertEquals(task.getCompleted(),true);

        taskService.deleteTask(taskDto);
        userService.delete(user.getId());
    }

}
