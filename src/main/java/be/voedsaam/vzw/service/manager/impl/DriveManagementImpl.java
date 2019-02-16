package be.voedsaam.vzw.service.manager.impl;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.service.dto.*;
import be.voedsaam.vzw.service.manager.DriveManagement;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import be.voedsaam.vzw.service.mapper.DriveMapper;
import be.voedsaam.vzw.service.mapper.TaskMapper;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DriveManagementImpl implements DriveManagement {
	private Collection<Drive> drives;
	private Collection<User> subscribeList;
	private DriveRepository driveRepository;
	private UserRepository userRepository;
	private DestinationRepository destinationRepository;
	private DriveMapper driveMapper;
	private UserMapper userMapper;
	private DestinationMapper destinationMapper;

	private TaskMapper taskMapper;
	@Autowired
	public DriveManagementImpl(DriveRepository driveRepository, DriveMapper driveMapper, UserMapper userMapper,
							   DestinationMapper destinationMapper, UserRepository userRepository, DestinationRepository destinationRepository) {
		super();
		this.driveRepository = driveRepository;
		this.driveMapper = driveMapper;
		this.userMapper = userMapper;
		this.destinationMapper = destinationMapper;
		this.userRepository = userRepository;
		this.taskMapper = new TaskMapper();
		this.destinationRepository = destinationRepository;
	}

	@Override
	public DriveDTO addDrive(DriveDTO driveDTO) {
		Drive drive = driveMapper.mapToObj(driveDTO);
		return driveMapper.mapToDTO(driveRepository.saveOrUpdate(drive));
	}

	@Override
	public boolean removeDrive(Long id) {
		 driveRepository.delete(id);
		 if (driveRepository.getById(id)==null)
		 	return true;
			return false;
	}

	@Override
	public DriveDTO getById(Long id) {
		return driveMapper.mapToDTO(driveRepository.getById(id));
	}

	@Override
	public List<DriveDTO> getDriveList(LocalDateTime from, LocalDateTime to) {
		List<Drive> drives = (List<Drive>) driveRepository.listAll();
		return driveMapper.mapToDTO(drives.stream().filter(d -> d.getStartTime().isAfter(from))
				.filter(d -> d.getEndTime().isBefore(to)).collect(Collectors.toList()));
	}

	@Override
	public DriveDTO setDriver(DriveDTO drive, UserDTO driver) {
		Drive found = driveRepository.getById(drive.getId());
		found.setDriver(userRepository.getById(driver.getId()));
		return driveMapper.mapToDTO(driveRepository.saveOrUpdate(found));
	}

	@Override
	public DriveDTO setAttendee(DriveDTO drive, UserDTO attendee) {
		Drive found = driveRepository.getById(drive.getId());
		found.setAttendee(userRepository.getById(attendee.getId()));
		return driveMapper.mapToDTO(driveRepository.saveOrUpdate(found));
	}

	@Override
	public DriveDTO setdepotHelp(DriveDTO drive, UserDTO depothelp) {
		Drive found = driveRepository.getById(drive.getId());
		found.setDepotHelp(userRepository.getById(depothelp.getId()));
		return driveMapper.mapToDTO(driveRepository.saveOrUpdate(found));
	}

	@Override
	public Collection<DriveDTO> getDrivesByDriver(UserDTO user) {
		List<Drive> drives = (List<Drive>) driveRepository.listAll();
		return driveMapper.mapToDTO(drives.stream().filter(d -> d.getDriver() != null)
				.filter(d -> d.getDriver().equals(userMapper.mapToObj(user))).collect(Collectors.toList()));
	}

	@Override
	public Collection<DriveDTO> getDrivesByAttendee(UserDTO user) {
		List<Drive> drives = (List<Drive>) driveRepository.listAll();
		return driveMapper.mapToDTO(drives.stream().filter(d -> d.getAttendee() != null)
				.filter(d -> d.getAttendee().equals(userMapper.mapToObj(user))).collect(Collectors.toList()));
	}

	@Override
	public Collection<DriveDTO> getDrivesByDepotHelp(UserDTO user) {
		List<Drive> drives = (List<Drive>) driveRepository.listAll();
		return driveMapper.mapToDTO(drives.stream().filter(d -> d.getDepotHelp() != null)
				.filter(d -> d.getDepotHelp().equals(userMapper.mapToObj(user))).collect(Collectors.toList()));
	}

	@Override
	public Collection<DriveDTO> getDrivesByDestination(DestinationDTO destination) {
		List<Drive> drives = (List<Drive>) driveRepository.listAll();
		return driveMapper.mapToDTO(drives.stream().filter(d -> d.getDestinations() != null)
				.filter(d -> d.getDestinations().contains(destinationMapper.mapToObj(destination)))
				.collect(Collectors.toList()));
	}

	@Override
	public DriveDTO addDrive(DriveDTO driveDTO, DestinationDTO start, DestinationDTO first) {
		Drive drive = driveMapper.mapToObj(driveDTO);
		// persist destination
		drive.getDestinations().add(destinationMapper.mapToObj(start));
		drive.getDestinations().add(destinationMapper.mapToObj(first));
		return driveMapper.mapToDTO(driveRepository.saveOrUpdate(drive));
	}

	@Override
	public DestinationDTO addDestination(DriveDTO drive1, DestinationDTO destinationDTO) {
		Drive drive = driveRepository.getById(drive1.getId());
		Destination destination = destinationRepository.saveOrUpdate(destinationMapper.mapToObj(destinationDTO));
		drive.getDestinations().add(destination);
		driveRepository.saveOrUpdate(drive);
		return destinationMapper.mapToDTO(destination);

	}

	@Override
	public Collection<DestinationDTO> getDestinationsByDrive(DriveDTO driveDTO) {
		List<Destination> results = new ArrayList<Destination>(
				driveRepository.getById(driveDTO.getId()).getDestinations());
		return destinationMapper.mapToDTO(results);
	}

	@Override
	public AgreementDTO addAgreement(DestinationDTO destinationDTO, AgreementDTO agreementDTO) {
		Destination destination = destinationRepository.getById(destinationDTO.getId());
		destination.getAgreements().add(agreementDTO.getAgreement());
		destination = destinationRepository.saveOrUpdate(destination);
		agreementDTO.setId((long) destination.getAgreements().size());
		return agreementDTO;
	}

	@Override
	public List<AgreementDTO> getAgreements(DestinationDTO destinationDTO) {
		Long counter = (long)1;
		Destination destination = destinationRepository.getById(destinationDTO.getId());
		List<AgreementDTO> agreements = new ArrayList<AgreementDTO>();
		List<String> strings = (List<String>) destination.getAgreements();
		for (String string : strings) {
			AgreementDTO a = new AgreementDTO();
			a.setId(counter);
			a.setAgreement(string);
			agreements.add(a);
			counter++;
		}
	
		return agreements;
	}

	@Override
	public TaskDTO addTask(DestinationDTO destinationDTO, TaskDTO taskDTO) {
		List<TaskDTO> tasks = getTasks(destinationDTO);
		if (tasks.contains(taskDTO))
			return taskDTO;
		tasks.add(taskDTO);
		Destination destination = destinationRepository.getById(destinationDTO.getId());
		destination.setTasks(taskMapper.mapToObj(tasks));
		destination = destinationRepository.saveOrUpdate(destination);
		for (Task task : destination.getTasks()) {
			if (task.getDiscription().equals(taskDTO.getDescription())&& task.getTitle().contentEquals(taskDTO.getTitle()))
				taskDTO = taskMapper.mapToDTO(task);
		}
		return taskDTO;
	
	}

	@Override
	public List<TaskDTO> getTasks(DestinationDTO detinationDTO) {
		return taskMapper.mapToDTO((List<Task>) destinationRepository.getById(detinationDTO.getId()).getTasks());
	}

	@Override
	public DestinationDTO addDestination(DestinationDTO destinationDTO) {
		Destination destination = destinationMapper.mapToObj(destinationDTO);	
		return destinationMapper.mapToDTO(destinationRepository.saveOrUpdate(destination));
	}
}
