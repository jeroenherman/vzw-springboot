package be.voedsaam.vzw.service.manager.impl;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.service.dto.*;
import be.voedsaam.vzw.service.manager.DriveManagement;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import be.voedsaam.vzw.service.mapper.DriveMapper;
import be.voedsaam.vzw.service.mapper.TaskMapper;
import be.voedsaam.vzw.service.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriveManagementImpl implements DriveManagement {
	private Collection<Drive> drives;
	private Collection<User> subscribeList;
	private DriveRepository driveRepository;
	private DriveMapper driveMapper;
	private UserMapper userMapper;
	private DestinationMapper destinationMapper;
	private UserRepository userRepository;
	private TaskMapper taskMapper;

	public DriveManagementImpl(DriveRepository driveRepository, DriveMapper driveMapper, UserMapper userMapper,
			DestinationMapper destinationMapper, UserRepository userRepository) {
		super();
		this.driveRepository = driveRepository;
		this.driveMapper = driveMapper;
		this.userMapper = userMapper;
		this.destinationMapper = destinationMapper;
		this.userRepository = userRepository;
		this.taskMapper = new TaskMapper();
	}

	@Override
	public DriveDTO addDrive(DriveDTO newDrive) {

		Drive drive = driveMapper.mapToObj(newDrive);
		drive.setAttendee(userRepository.findByName(drive.getAttendee()));
		drive.setDepotHelp(userRepository.findByName(drive.getDepotHelp()));
		drive.setDriver(userRepository.findByName(drive.getDriver()));
		if (drive.getDriver() == null)
			return null;
		return driveMapper.mapToDTO(driveRepository.create(drive));
	}

	@Override
	public boolean removeDrive(DriveDTO oldDrive) {
		return driveRepository.delete(driveMapper.mapToObj(oldDrive));
	}

	@Override
	public List<DriveDTO> getDriveList(LocalDateTime from, LocalDateTime to) {
		return driveMapper.mapToDTO(driveRepository.getAll().stream().filter(d -> d.getStartTime().isAfter(from))
				.filter(d -> d.getEndTime().isBefore(to)).collect(Collectors.toList()));
	}

	@Override
	public Collection<DriveDTO> getDrivesByDriver(UserDTO user) {
		return driveMapper.mapToDTO(driveRepository.getAll().stream().filter(d -> d.getDriver() != null)
				.filter(d -> d.getDriver().equals(userMapper.mapToObj(user))).collect(Collectors.toList()));
	}

	@Override
	public Collection<DriveDTO> getDrivesByAttendee(UserDTO user) {
		return driveMapper.mapToDTO(driveRepository.getAll().stream().filter(d -> d.getAttendee() != null)
				.filter(d -> d.getAttendee().equals(userMapper.mapToObj(user))).collect(Collectors.toList()));
	}

	@Override
	public Collection<DriveDTO> getDrivesByDepotHelp(UserDTO user) {
		return driveMapper.mapToDTO(driveRepository.getAll().stream().filter(d -> d.getDepotHelp() != null)
				.filter(d -> d.getDepotHelp().equals(userMapper.mapToObj(user))).collect(Collectors.toList()));
	}

	@Override
	public Collection<DriveDTO> getDrivesByDestination(DestinationDTO destination) {
		return driveMapper.mapToDTO(driveRepository.getAll().stream().filter(d -> d.getDestinations() != null)
				.filter(d -> d.getDestinations().contains(destinationMapper.mapToObj(destination)))
				.collect(Collectors.toList()));
	}

	@Override
	public DriveDTO changeDrive(DriveDTO newDriveDTO) {
		Drive newDrive = driveMapper.mapToObj(newDriveDTO);
		Drive oldDrive = driveRepository.getByID(newDriveDTO.getId());
		oldDrive = newDrive;
		return driveMapper.mapToDTO(driveRepository.update(oldDrive));
	}

	@Override
	public DriveDTO addDrive(DriveDTO drive1, DestinationDTO start, DestinationDTO first) {
		Drive drive = driveMapper.mapToObj(drive1);
		drive.setAttendee(userRepository.findByName(drive.getAttendee()));
		drive.setDepotHelp(userRepository.findByName(drive.getDepotHelp()));
		drive.setDriver(userRepository.findByName(drive.getDriver()));
		// persist destination
		drive.getDestinations().add(destinationMapper.mapToObj(start));
		drive.getDestinations().add(destinationMapper.mapToObj(first));
		if (driveRepository.exists(drive))
			return driveMapper.mapToDTO(driveRepository.update(drive));
		return driveMapper.mapToDTO(driveRepository.create(drive));
	}

	@Override
	public DestinationDTO addDestination(DriveDTO drive1, DestinationDTO destinationDTO) {
		Drive drive = driveRepository.find(driveMapper.mapToObj(drive1));
		Destination destination = driveRepository.addDestination(destinationMapper.mapToObj(destinationDTO));
		drive.getDestinations().add(destination);

		if (driveRepository.exists(drive))
			driveMapper.mapToDTO(driveRepository.update(drive));
		driveMapper.mapToDTO(driveRepository.create(drive));

		return destinationMapper.mapToDTO(destination);

	}

	@Override
	public Collection<DestinationDTO> getDestinationsByDrive(DriveDTO driveDTO) {

		List<Destination> results = new ArrayList<Destination>(
				driveRepository.getByID(driveDTO.getId()).getDestinations());

		return destinationMapper.mapToDTO(results);
	}

	@Override
	public AgreementDTO addAgreement(DestinationDTO destinationDTO, AgreementDTO agreementDTO) {
		Destination destination = driveRepository.findDestinationById(destinationDTO.getId());
		destination.getAgreements().add(agreementDTO.getAgreement());
		destination = driveRepository.addDestination(destination);
		agreementDTO.setId((long) destination.getAgreements().size());
		return agreementDTO;
	}

	@Override
	public List<AgreementDTO> getAgreements(DestinationDTO destinationDTO) {
		Long counter = (long)1;
		Destination destination = driveRepository.findDestinationById(destinationDTO.getId());
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
		Destination destination = driveRepository.findDestinationById(destinationDTO.getId());
		destination.setTasks(taskMapper.mapToObj(tasks));
		destination = driveRepository.addDestination(destination);
		for (Task task : destination.getTasks()) {
			if (task.getDiscription().equals(taskDTO.getDescription())&& task.getTitle().contentEquals(taskDTO.getTitle()))
				taskDTO = taskMapper.mapToDTO(task);
		}
			
		return taskDTO;
	
	}

	@Override
	public List<TaskDTO> getTasks(DestinationDTO detinationDTO) {
	
		return taskMapper.mapToDTO((List<Task>)driveRepository.findDestinationById(detinationDTO.getId()).getTasks());
	}

	@Override
	public DestinationDTO addDestination(DestinationDTO destinationDTO) {
		Destination destination = destinationMapper.mapToObj(destinationDTO);	
		return destinationMapper.mapToDTO(driveRepository.addDestination(destination));
	}

	

}
