package root.timemanagementapp.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean completed;

    @Column
    private Long estimatedTimeInSeconds;

    @Column
    private Long elapsedTimeInSeconds;

    @Column
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id", nullable = false)
    @JsonIgnore
    private Sprint sprint;

    @Column
    private Boolean highPriority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Task() {
    }

    public Task(String name, Sprint sprint) {
        this.name = name;
        this.completed = false;
        this.sprint = sprint;
        this.highPriority = false;

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

    public Boolean getHighPriority() {
        return highPriority;
    }

    public void setHighPriority(Boolean highPriority) {
        this.highPriority = highPriority;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getEstimatedTimeInSeconds() {
        return estimatedTimeInSeconds;
    }

    public void setEstimatedTimeInSeconds(Long estimatedTimeInSeconds) {
        this.estimatedTimeInSeconds = estimatedTimeInSeconds;
    }

    public Long getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    public void setElapsedTimeInSeconds(Long elapsedTimeInSeconds) {
        this.elapsedTimeInSeconds = elapsedTimeInSeconds;
    }

}
