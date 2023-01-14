package root.timemanagementapp.dto;

import java.time.LocalTime;

public class TaskDto {

    private Long id;

    private String name;

    private Boolean completed;

    private LocalTime estimatedTime;

    private LocalTime elapsedTime;

    private String note;

    private Long sprintId;

    private Long userId;

    private Boolean highPriority;

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

    public LocalTime getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalTime estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public LocalTime getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(LocalTime elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", completed=" + completed +
                ", estimatedTime=" + estimatedTime +
                ", elapsedTime=" + elapsedTime +
                ", note='" + note + '\'' +
                ", sprintId=" + sprintId +
                ", userId=" + userId +
                ", highPriority=" + highPriority +
                '}';
    }
}
