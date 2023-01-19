package root.timemanagementapp.dto;

import root.timemanagementapp.database.model.Sprint;
import root.timemanagementapp.database.model.Task;

public class StatisticsDto {
    private final long timeOverruns;

    private final long completedTasksCount;

    private final long inWorkTasksCount;

    public StatisticsDto(Sprint sprint) {
        timeOverruns = (long) ((sprint.getTasks().stream().mapToDouble(Task::getElapsedTimeInSeconds).sum() /
                sprint.getTasks().stream().mapToDouble(Task::getEstimatedTimeInSeconds).sum()) * 100);
        completedTasksCount = sprint.getTasks().stream().filter(Task::getCompleted).count();
        inWorkTasksCount = sprint.getTasks().stream().filter(Task -> !Task.getCompleted()).count();
    }

    public long getTimeOverruns() {
        return timeOverruns;
    }

    public long getCompletedTasksCount() {
        return completedTasksCount;
    }

    public long getInWorkTasksCount() {
        return inWorkTasksCount;
    }

    public long getTimeOverrunsPercent() {
        if (timeOverruns > 100) {
            return timeOverruns - 100;
        } else {
            return 0;
        }
    }

    public boolean isNormalStatus() {
        return timeOverruns < 50;
    }

    public boolean isPrimaryStatus() {
        return timeOverruns >= 50 && timeOverruns < 80;
    }

    public boolean isWarningStatus() {
        return timeOverruns >= 80 && timeOverruns < 100;
    }

    public boolean isDangerStatus() {
        return timeOverruns > 100;
    }
}


