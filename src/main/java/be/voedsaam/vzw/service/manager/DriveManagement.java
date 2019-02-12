package be.voedsaam.vzw.service.manager;

import be.voedsaam.vzw.service.dto.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface DriveManagement {
	
	public DriveDTO addDrive(DriveDTO newDrive);
	public boolean removeDrive(DriveDTO oldDrive);
	public Collection<DriveDTO> getDriveList(LocalDateTime from, LocalDateTime to);
	public Collection<DriveDTO> getDrivesByDriver(UserDTO user);
	public Collection<DriveDTO> getDrivesByAttendee(UserDTO user);
	public Collection<DriveDTO> getDrivesByDepotHelp(UserDTO user);
	public Collection<DriveDTO> getDrivesByDestination(DestinationDTO destination);
	public DriveDTO changeDrive(DriveDTO newDrive);
	public DriveDTO addDrive(DriveDTO drive1, DestinationDTO start, DestinationDTO first);
	public DestinationDTO addDestination(DriveDTO drive1, DestinationDTO destination3);
	public Collection<DestinationDTO> getDestinationsByDrive(DriveDTO driveDTO);
	public AgreementDTO addAgreement(DestinationDTO destinationDTO, AgreementDTO agreementDTO);
	public List<AgreementDTO> getAgreements(DestinationDTO destinationDTO);
	public TaskDTO addTask(DestinationDTO destinationDTO, TaskDTO taskDTO);
	public List<TaskDTO> getTasks(DestinationDTO detinationDTO);
	public DestinationDTO addDestination(DestinationDTO destinationDTO);
	

}
