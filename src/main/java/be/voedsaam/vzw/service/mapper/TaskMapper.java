package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper extends AbstractMapper<Task, TaskDTO> {

	@Override
	public TaskDTO mapToDTO(Task b) {
		if (b ==null)
		return null;
		TaskDTO d = new TaskDTO();
		
		return d;
	}

	@Override
	public Task mapToObj(TaskDTO d) {
		if (d == null)
		return null;
		Task b = new Task();
		
		return b;
	}
	

}
