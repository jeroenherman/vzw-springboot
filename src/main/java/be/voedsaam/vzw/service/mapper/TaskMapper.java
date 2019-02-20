package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.business.repository.TaskRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.crypto.Des;

import java.util.Optional;

@Component
public class TaskMapper extends AbstractMapper<Task, TaskDTO> {

	private TaskRepository taskRepository;
	@Autowired
	public void setTaskRepository(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public TaskDTO mapToDTO(Task b) {
		if (b ==null)
		return null;
		TaskDTO d = new TaskDTO();
		d.setId(b.getId());
		d.setDescription(b.getDiscription());
		d.setRole(b.getRole());
		d.setTitle(b.getTitle());
		return d;
	}

	@Override
	public Task mapToObj(TaskDTO d) {
		if (d == null)
		return null;
		Task b = new Task();
		Optional<Task> o = Optional.empty();
		if (d.getId()!=null)
			o = taskRepository.findById(d.getId());
		if (o.isPresent())
			b= o.get();
		b.setDiscription(d.getDescription());
		b.setRole(d.getRole());
		b.setTitle(d.getTitle());
		return b;
	}
	

}
