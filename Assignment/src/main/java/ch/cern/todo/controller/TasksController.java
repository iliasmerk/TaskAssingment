package ch.cern.todo.controller;

import ch.cern.todo.controller.request.view.TaskView;
import ch.cern.todo.model.Task;
import ch.cern.todo.repository.TaskRepository;
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
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TasksCategoryRepository tasksCategoryRepository;


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskView create(@RequestBody TaskView taskView) {
        Task task = new Task();
        task.setName(taskView.getName());
        task.setDescription(taskView.getDescription());
        task.setDeadline(taskView.getDeadline());
        task.setCategory(tasksCategoryRepository.findById(taskView.getCategoryId()).orElse(null));
        return mapFromTask(taskRepository.save(task));
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskView update(@RequestBody TaskView taskView) {
        Task task = taskRepository.findById(taskView.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task with id: %d does not Exist!", taskView.getId())));
        task.setName(taskView.getName());
        task.setDescription(taskView.getDescription());
        task.setDeadline(taskView.getDeadline());
        task.setCategory(tasksCategoryRepository.findById(taskView.getCategoryId()).orElse(null));
        return mapFromTask(taskRepository.save(task));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskView> getAll() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false).map(this::mapFromTask).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskView get(@PathVariable long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task with id: %d does not Exist!", id)));
        TaskView taskView = new TaskView();
        taskView.setId(task.getId());
        taskView.setName(task.getName());
        taskView.setDescription(task.getDescription());
        taskView.setDeadline(task.getDeadline());
        taskView.setCategoryId(task.getCategory().getId());
        return taskView;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable long id) {
        try {
            taskRepository.deleteById(id);
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
        if (task.getCategory()!=null){
            taskView.setCategoryId(task.getCategory().getId());
        }
        return taskView;
    }
}