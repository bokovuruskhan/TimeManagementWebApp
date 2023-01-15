package root.timemanagementapp.database.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean active;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Task> tasks;

    public Sprint() {
    }

    public Sprint(String name) {
        this.name = name;
        this.active = true;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }

    public long getCompletedTasksCount(){
        return tasks.stream().filter(Task::isCompleted).count();
    }

    public long getInWorkTasksCount(){
        return tasks.stream().filter(Task->!Task.isCompleted()).count();
    }

    public Double getTimeOverruns(){
        return Math.ceil((tasks.stream().mapToDouble(Task->Task.getElapsedTime().toSecondOfDay()).sum() /
                tasks.stream().mapToDouble(Task->Task.getEstimatedTime().toSecondOfDay()).sum())*100);
    }

}
