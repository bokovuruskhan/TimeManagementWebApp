package root.timemanagementapp.dto;

import root.timemanagementapp.database.model.Sprint;
import root.timemanagementapp.database.model.Task;

public class StatisticsDto {

    private final long x;

    private final long y;

    private final long c1;
    private final long c2;

    private final long e;

    public StatisticsDto(Sprint sprint) {
        x = sprint.getTasks().stream().filter(Task -> !Task.getCompleted()).map(TaskDto::new).mapToLong(TaskDto::getEstimatedHours).sum();
        y = sprint.getTasks().stream().filter(Task -> !Task.getCompleted()).map(TaskDto::new).mapToLong(TaskDto::getElapsedHours).sum();
        c1 = sprint.getTasks().stream().map(Task::getUser).count();
        c2 = sprint.getTasks().size();
        e = sprint.getTasks().stream().map(TaskDto::new).mapToLong(TaskDto::getEstimatedHours).sum();
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getC1() {
        return c1;
    }

    public long getC2() {
        return c2;
    }

    public long getE() {
        return e;
    }
}
