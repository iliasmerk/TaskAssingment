package ch.cern.todo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TASK_CATEGORIES")
public class TasksCategory {
    @Id
    @Column(name = "CATEGORY_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CATEGORY_NAME", length = 100, nullable = false)
    private String name;
    @Column(name = "CATEGORY_DESCRIPTION", length = 500, nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Task> tasks;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

