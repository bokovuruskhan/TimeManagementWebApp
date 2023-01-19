package root.timemanagementapp.dto;

import root.timemanagementapp.database.model.Task;

public class TaskDto {

    private Long id;

    private String name;

    private Boolean completed;

    private Long sprintId;

    private Long userId;

    private Boolean highPriority;

    private String note;

    private Long estimatedHours;

    private Long estimatedMinutes;

    private Long estimatedSeconds;

    private Long elapsedHours;

    private Long elapsedMinutes;

    private Long elapsedSeconds;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.completed = task.getCompleted();
        this.sprintId = task.getSprint().getId();
        this.userId = task.getUser().getId();
        this.highPriority = task.getHighPriority();
        this.note = task.getNote();

        this.estimatedHours = task.getEstimatedTimeInSeconds() / 3600;
        this.estimatedMinutes = task.getEstimatedTimeInSeconds() / 60 % 60;
        this.estimatedSeconds = task.getEstimatedTimeInSeconds() % 60;

        this.elapsedHours = task.getElapsedTimeInSeconds() / 3600;
        this.elapsedMinutes = task.getElapsedTimeInSeconds() / 60 % 60;
        this.elapsedSeconds = task.getElapsedTimeInSeconds() % 60;

    }

    public TaskDto() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean isHighPriority() {
        return highPriority;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getHighPriority() {
        return highPriority;
    }

    public void setHighPriority(Boolean highPriority) {
        this.highPriority = highPriority;
    }

    public Long getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Long estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Long getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(Long estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public Long getEstimatedSeconds() {
        return estimatedSeconds;
    }

    public void setEstimatedSeconds(Long estimatedSeconds) {
        this.estimatedSeconds = estimatedSeconds;
    }

    public Long getElapsedHours() {
        return elapsedHours;
    }

    public void setElapsedHours(Long elapsedHours) {
        this.elapsedHours = elapsedHours;
    }

    public Long getElapsedMinutes() {
        return elapsedMinutes;
    }

    public void setElapsedMinutes(Long elapsedMinutes) {
        this.elapsedMinutes = elapsedMinutes;
    }

    public Long getElapsedSeconds() {
        return elapsedSeconds;
    }

    public void setElapsedSeconds(Long elapsedSeconds) {
        this.elapsedSeconds = elapsedSeconds;
    }

    public Long getEstimatedTimeInSeconds() {
        long result = 0L;
        result += estimatedHours * 60 * 60;
        result += estimatedMinutes * 60;
        result += estimatedHours;
        return result;
    }

    public Long getElapsedTimeInSeconds() {
        long result = 0L;
        result += elapsedHours * 60 * 60;
        result += elapsedMinutes * 60;
        result += elapsedSeconds;
        return result;
    }

    public String getEstimatedTimeString() {
        String hours;
        String minutes;
        String seconds;
        if (estimatedHours < 10) {
            hours = String.format("0%d", estimatedHours);
        } else {
            hours = String.format("%d", estimatedHours);
        }
        if (estimatedMinutes < 10) {
            minutes = String.format("0%d", estimatedMinutes);
        } else {
            minutes = String.format("%d", estimatedMinutes);
        }
        if (estimatedSeconds < 10) {
            seconds = String.format("0%d", estimatedSeconds);
        } else {
            seconds = String.format("%d", estimatedSeconds);
        }
        return String.format("%s:%s:%s", hours, minutes, seconds);
    }

    public String getElapsedTimeString() {
        String hours;
        String minutes;
        String seconds;
        if (elapsedHours < 10) {
            hours = String.format("0%d", elapsedHours);
        } else {
            hours = String.format("%d", elapsedHours);
        }
        if (elapsedMinutes < 10) {
            minutes = String.format("0%d", elapsedMinutes);
        } else {
            minutes = String.format("%d", elapsedMinutes);
        }
        if (elapsedSeconds < 10) {
            seconds = String.format("0%d", elapsedSeconds);
        } else {
            seconds = String.format("%d", elapsedSeconds);
        }
        return String.format("%s:%s:%s", hours, minutes, seconds);
    }

    public boolean isNormalStatus() {
        return getElapsedTimeInSeconds() < (getEstimatedTimeInSeconds() * 0.5);
    }

    public boolean isPrimaryStatus() {
        return getElapsedTimeInSeconds() >= (getEstimatedTimeInSeconds() * 0.5) && getElapsedTimeInSeconds() < (getEstimatedTimeInSeconds() * 0.8);
    }

    public boolean isWarningStatus() {
        return getElapsedTimeInSeconds() >= (getEstimatedTimeInSeconds() * 0.8) && getElapsedTimeInSeconds() < (getEstimatedTimeInSeconds());
    }

    public boolean isDangerStatus() {
        return getElapsedTimeInSeconds() >= getEstimatedTimeInSeconds();
    }

}
