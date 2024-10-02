package com.project.todolist.controller;

import com.project.todolist.entity.Tasks;
import com.project.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // create new task
    @PostMapping("/task")
    public ResponseEntity<Tasks> saveTask(@RequestBody Tasks task) {
        Tasks savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }

    // get all tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<Tasks>> getAllTasks() {
        List<Tasks> tasks = taskService.fetchAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // get task by id
    @GetMapping("/task/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable long id) {
        Optional<Tasks> tasksOptional = taskService.fetchTaskById(id);
        if (tasksOptional.isPresent()) {
            return ResponseEntity.ok(tasksOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // update a task
    @PutMapping("/task/{id}")
    public ResponseEntity<Tasks> updateTask(@PathVariable long id, @RequestBody Tasks updatedTask) {
        try {
            Tasks task = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //delete a task
    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<Tasks> deleteTask(@PathVariable long id) {
        boolean deletionStatus = taskService.deleteTaskById(id);
        if (deletionStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
