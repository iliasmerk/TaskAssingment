package ch.cern.todo.controller;

import ch.cern.todo.controller.request.view.TaskView;
import ch.cern.todo.controller.request.view.TasksCategoryView;
import ch.cern.todo.model.Task;
import ch.cern.todo.model.TasksCategory;
import ch.cern.todo.repository.TasksCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class TasksCategoriesController {

    @Autowired
    private TasksCategoryRepository tasksCategoryRepository;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TasksCategoryView create(@RequestBody TasksCategoryView tasksCategoryView) {
        TasksCategory tasksCategory = new TasksCategory();
        tasksCategory.setName(tasksCategoryView.getName());
        tasksCategory.setDescription(tasksCategoryView.getDescription());
        return mapFromTaskCategory(tasksCategoryRepository.save(tasksCategory));
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TasksCategoryView update(@RequestBody TasksCategoryView tasksCategoryView) {
        TasksCategory tasksCategory = tasksCategoryRepository.findById(tasksCategoryView.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id: %d does not Exist!", tasksCategoryView.getId())));
        tasksCategory.setName(tasksCategoryView.getName());
        if (tasksCategoryView.getTasks() != null) {
            tasksCategory.setTasks(tasksCategoryView.getTasks().stream().map(this::mapFromTaskView).collect(Collectors.toList()));
        }
        tasksCategory.setDescription(tasksCategoryView.getDescription());
        return mapFromTaskCategory(tasksCategoryRepository.save(tasksCategory));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TasksCategoryView get(@PathVariable long id) {
        TasksCategory tasksCategory = tasksCategoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id: %d does not Exist!", id)));
        return mapFromTaskCategory(tasksCategory);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TasksCategoryView> getAll() {
        return StreamSupport.stream(tasksCategoryRepository.findAll().spliterator(), false).map(this::mapFromTaskCategory).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable long id) {
        try {
            tasksCategoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id: %d Not Found", id));
        }

    }

    private TaskView mapFromTask(Task task) {
        TaskView taskView = new TaskView();
        taskView.setId(task.getId());
        taskView.setName(task.getName());
        taskView.setDescription(task.getDescription());
        taskView.setDeadline(task.getDeadline());
        taskView.setCategoryId(task.getCategory().getId());
        return taskView;
    }

    private Task mapFromTaskView(TaskView taskView) {
        Task task = new Task();
        task.setId(taskView.getId());
        task.setName(taskView.getName());
        task.setDescription(taskView.getDescription());
        task.setDeadline(taskView.getDeadline());
        task.setCategory(tasksCategoryRepository.findById(taskView.getCategoryId()).orElse(null));
        return task;
    }

    private TasksCategoryView mapFromTaskCategory(TasksCategory tasksCategory) {
        TasksCategoryView tasksCategoryView = new TasksCategoryView();
        tasksCategoryView.setId(tasksCategory.getId());
        tasksCategoryView.setName(tasksCategory.getName());
        tasksCategoryView.setDescription(tasksCategory.getDescription());
        tasksCategoryView.setTasks(tasksCategory.getTasks().stream().map(this::mapFromTask).collect(Collectors.toList()));
        return tasksCategoryView;
    }
}