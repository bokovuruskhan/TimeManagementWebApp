package root.timemanagementapp.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalTime;

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
    private LocalTime estimatedTime;

    @Column
    private LocalTime elapsedTime;

    @Column
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id", nullable = false)
    @JsonIgnore
    private Sprint sprint;



    @Column
    private Boolean highPriority;

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

    public Boolean isHighPriority() {
        return highPriority;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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
}
