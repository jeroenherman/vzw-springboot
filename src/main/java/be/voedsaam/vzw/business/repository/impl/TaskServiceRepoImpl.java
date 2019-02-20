package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.business.repository.TaskRepository;
import be.voedsaam.vzw.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceRepoImpl implements TaskService{

    private TaskRepository taskRepository;
    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<?> listAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task saveOrUpdate(Task domainObject) {
        return taskRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
