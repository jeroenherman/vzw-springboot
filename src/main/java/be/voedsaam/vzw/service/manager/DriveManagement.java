package be.voedsaam.vzw.service.manager;

import be.voedsaam.vzw.service.dto.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface DriveManagement {
	
	DriveDTO addDrive(DriveDTO newDrive);
	boolean removeDrive(Long id);
	DriveDTO getById(Long id);
	Collection<DriveDTO> getDriveList(LocalDateTime from, LocalDateTime to);
	DriveDTO setDriver(DriveDTO drive ,UserDTO driver);
	DriveDTO setAttendee(DriveDTO drive ,UserDTO attendee);
	DriveDTO setdepotHelp(DriveDTO drive ,UserDTO depothelp);
	Collection<DriveDTO> getDrivesByDriver(UserDTO user);
	Collection<DriveDTO> getDrivesByAttendee(UserDTO user);
	Collection<DriveDTO> getDrivesByDepotHelp(UserDTO user);
	Collection<DriveDTO> getDrivesByDestination(DestinationDTO destination);
	DriveDTO addDrive(DriveDTO drive1, DestinationDTO start, DestinationDTO first);
	DestinationDTO addDestination(DriveDTO drive1, DestinationDTO destination3);
	Collection<DestinationDTO> getDestinationsByDrive(DriveDTO driveDTO);
	AgreementDTO addAgreement(DestinationDTO destinationDTO, AgreementDTO agreementDTO);
	List<AgreementDTO> getAgreements(DestinationDTO destinationDTO);
	TaskDTO addTask(DestinationDTO destinationDTO, TaskDTO taskDTO);
	List<TaskDTO> getTasks(DestinationDTO detinationDTO);
	DestinationDTO addDestination(DestinationDTO destinationDTO);
	

}
