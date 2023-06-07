package dev.bitan.springmongoatlas.service;

import dev.bitan.springmongoatlas.model.Task;
import dev.bitan.springmongoatlas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    // CRUD

    //Create
    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }


    // Read
    public List<Task> findAllTask() {
        return repository.findAll();
    }

    public Task getTaskByTaskId(String taskId) {
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity) {
        return repository.findBySeverity(severity);
    }

    // By Query
    public List<Task> getTaskByAssignee(String assignee){
        return repository.getTaskByAssignee(assignee);
    }

    // Update
    public Task updateTask(Task taskRequest) {
        // get the existing doc from db
        // populate new value from req to existing obj/entity/doc

        Task existingTask = repository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());

        return repository.save(existingTask);

    }

    // Delete

    public String deleteTask(String taskId) {
        repository.deleteById(taskId);
        return taskId +" task deleted from dashboard";
    }











}
