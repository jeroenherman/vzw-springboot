package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.business.repository.TaskRepository;
import be.voedsaam.vzw.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
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
        Optional<Task> o = taskRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }
    @Override
    public Task saveOrUpdate(Task domainObject) {
        return taskRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Task> o = taskRepository.findById(id);
            if (o.isPresent())
                taskRepository.delete(o.get());
        }
    }
}
