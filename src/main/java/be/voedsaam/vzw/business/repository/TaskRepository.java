package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {

}
