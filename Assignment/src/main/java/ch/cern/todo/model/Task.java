package ch.cern.todo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @Column(name = "TASK_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TASK_NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "TASK_DESCRIPTION", length = 100, nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private TasksCategory category;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TasksCategory getCategory() {
        return category;
    }

    public void setCategory(TasksCategory category) {
        this.category = category;
    }
}
