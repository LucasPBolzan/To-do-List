package com.project.todolist.service;

import com.project.todolist.entity.Tasks;
import com.project.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private static TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //create task
    public Tasks saveTask(Tasks task) {
        return taskRepository.save(task);
    }

    //get all tasks
    public static List<Tasks> fetchAllTasks() {
        return taskRepository.findAll();
    }

    //get task by id
    public Optional<Tasks> fetchTaskById(long id) {
        return taskRepository.findById(id);
    }

    //update task
    public Tasks updateTask(long id, Tasks updatedTask) {
        Optional<Tasks> existingTaskOpt = taskRepository.findById(id);

        if (existingTaskOpt.isPresent()) {
            Tasks existingTask = existingTaskOpt.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDueDate(updatedTask.getDueDate());

            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Failed to update task: " + id);
        }
    }

    //delete task
    public boolean deleteTaskById(long id) {
        taskRepository.deleteById(id);
        return true;
    }
}



