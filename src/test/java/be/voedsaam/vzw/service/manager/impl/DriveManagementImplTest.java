package be.voedsaam.vzw.service.manager.impl;

import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.business.repository.impl.mysql.DestinationRepositoryMySQL;
import be.voedsaam.vzw.business.repository.impl.mysql.DriveRepositoryMySQL;
import be.voedsaam.vzw.business.repository.impl.mysql.UserRepositoryMySQL;
import be.voedsaam.vzw.commons.Role;
import be.voedsaam.vzw.service.dto.*;
import be.voedsaam.vzw.service.manager.DriveManagement;
import be.voedsaam.vzw.service.manager.VzwManagement;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import be.voedsaam.vzw.service.mapper.DriveMapper;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DriveManagementImplTest {
	private DriveManagement driveManagement;
	private DriveRepository driveRepository;
	private DestinationRepository destinationRepository;
	private VzwManagement vzw;
	private UserRepository userRepository;
	private DriveMapper driveMapper;
	private UserMapper userMapper;
	private DestinationMapper destinationMapper;
	
	private DriveDTO drive1;
	private DriveDTO drive2;
	private DriveDTO drive3;
	private DriveDTO drive4;
	private DestinationDTO destination1;
	private DestinationDTO destination2;
	private DestinationDTO destination3;
	private UserDTO driver; 
	private UserDTO attendee;
	private UserDTO depotHelp;
	
	
	private void dependencyInjection() {
		driveRepository = new DriveRepositoryMySQL();
		userRepository = new UserRepositoryMySQL();
		destinationRepository = new DestinationRepositoryMySQL();
		userMapper = new UserMapper();
		destinationMapper = new DestinationMapper();
		driveMapper = new DriveMapper();
		driveManagement = new DriveManagementImpl(driveRepository,driveMapper,userMapper,destinationMapper, userRepository, destinationRepository);
		vzw = new VzwManagementImpl(userMapper,userRepository);
		destination1 = new DestinationDTO();
		destination2 = new DestinationDTO();
		destination3 = new DestinationDTO();
		
	}

	@Test
	public void testAddDrive() throws Exception {
		drive1 = new DriveDTO();

		drive1.setStartTime(LocalDateTime.of(2019, 01, 01, 9, 30));
		drive1.setEndTime(LocalDateTime.of(2019, 01, 01, 10, 30));
	
		assertEquals("driver user is not yet created",null, driveManagement.addDrive(drive1));
		assertEquals("driver must have 0 drives User is not created yet", driveManagement.getDrivesByDriver(driver).size(),0,0);
		
		assertEquals("attendee must have 0 drives User is not created yet", driveManagement.getDrivesByAttendee(attendee).size(),0,0);
		assertEquals("depothelp must have 0 drives User is not created yet", driveManagement.getDrivesByDepotHelp(depotHelp).size(),0,0);
		assertEquals("Number of drives is 0", driveManagement.getDriveList(LocalDateTime.MIN,  LocalDateTime.MAX).size(),0,0);
		vzw.addUser(attendee);
		vzw.addUser(driver);
		vzw.addUser(depotHelp);
		assertEquals(null,drive1.getId());
		drive1 = driveManagement.addDrive(drive1);
		assertTrue(drive1.getId()!=null);
		assertTrue(drive1.getStartTime().equals((LocalDateTime.of(2019, 01, 01, 9, 30))));
		assertTrue(drive1.getEndTime().equals((LocalDateTime.of(2019, 01, 01, 10, 30))));
		
		
		assertEquals("driver must have 1 drives User is  created", driveManagement.getDrivesByDriver(driver).size(),1,0);
		assertEquals("attendee must have 1 drives User is  created ", driveManagement.getDrivesByAttendee(attendee).size(),1,0);
		assertEquals("depothelp must have 1  drives User is  created ", driveManagement.getDrivesByDepotHelp(depotHelp).size(),1,0);
		assertEquals("Number of drives is 1",1, driveManagement.getDriveList(LocalDateTime.MIN,  LocalDateTime.MAX).size(),0);
		drive2 =new DriveDTO();
		drive2.setStartTime(LocalDateTime.of(2019, 02, 01, 9, 30));
		drive2.setEndTime(LocalDateTime.of(2019, 02, 01, 10, 30));
		
		assertEquals(null,drive2.getId());
		drive2 = driveManagement.addDrive(drive2);
		assertTrue(drive2.getId()!=null);
	
		
		assertEquals("drivemanagement must contain 2 drives  ",2, driveManagement.getDriveList(LocalDateTime.MIN, LocalDateTime.MAX).size());
		assertEquals("driver must have 2 drives User is  created", driveManagement.getDrivesByDriver(driver).size(),2,0);
		assertEquals("attendee must have 2 drives User is  created ", driveManagement.getDrivesByAttendee(attendee).size(),2,0);
		assertEquals("depothelp must have 1  drives User is  created ", driveManagement.getDrivesByDepotHelp(depotHelp).size(),1,0);
		assertEquals("Number of drives is 2",2 ,driveManagement.getDriveList(LocalDateTime.MIN,  LocalDateTime.MAX).size(),0);
		assertEquals("Number of drives in 2019-02 is 1", driveManagement.getDriveList(LocalDateTime.of(2019, 02, 01, 0, 0),  LocalDateTime.of(2019, 02, 28, 0, 00)).size(),1,0);
		
		drive1 = driveManagement.addDrive(drive1, destination1, destination2);
		assertEquals("drive1 has 2 destinations",2, driveManagement.getDestinationsByDrive(drive1).size());
		destination1 = ((List<DestinationDTO>) driveManagement.getDestinationsByDrive(drive1)).get(0);
		assertTrue(destination1.getId()!=null);
		destination2 = ((List<DestinationDTO>) driveManagement.getDestinationsByDrive(drive1)).get(1);
		assertTrue(destination2.getId()!=null);
		assertEquals("Sint-Niklaas", destination1.getCity()); 
		assertEquals(9100, destination1.getPostalCode(),0); 
		assertEquals("LamStraat", destination1.getStreet());
		assertEquals(113, destination1.getStreetNumber(),0); 
		assertEquals("Els van de Steene:  03 780 52 35", destination1.getContactInfo());
		assertEquals("Vzw Voedsaam", destination1.getDestinationName());
		
		assertEquals("Temse", destination2.getCity()); 
		assertEquals(9140, destination2.getPostalCode(),0); 
		assertEquals("LamStraat", destination1.getStreet());
		assertEquals(247, destination2.getStreetNumber(),0); 
		assertEquals("Els van de Steene:  03 780 52 35", destination2.getContactInfo());
		assertEquals("Voedsel Depot: Den azalee", destination2.getDestinationName());
		
		// add destination to existing drive drive 1 has 3 destinations 
		destination3 =driveManagement.addDestination(drive1, destination3);
		
		destination1 = ((List<DestinationDTO>) driveManagement.getDestinationsByDrive(drive1)).get(0);
		destination2 = ((List<DestinationDTO>) driveManagement.getDestinationsByDrive(drive1)).get(1);
		
		assertEquals("Sint-Niklaas", destination1.getCity()); 
		assertEquals(9100, destination1.getPostalCode(),0); 
		assertEquals("LamStraat", destination1.getStreet());
		assertEquals(113, destination1.getStreetNumber(),0); 
		assertEquals("Els van de Steene:  03 780 52 35", destination1.getContactInfo());
		assertEquals("Vzw Voedsaam", destination1.getDestinationName());
		
		assertEquals("Temse", destination2.getCity()); 
		assertEquals(9140, destination2.getPostalCode(),0); 
		assertEquals("LamStraat", destination1.getStreet());
		assertEquals(247, destination2.getStreetNumber(),0); 
		assertEquals("Els van de Steene:  03 780 52 35", destination2.getContactInfo());
		assertEquals("Voedsel Depot: Den azalee", destination2.getDestinationName());
		destination3 = ((List<DestinationDTO>) driveManagement.getDestinationsByDrive(drive1)).get(2);
		assertTrue(destination3.getId()!=null);
		assertEquals("Sint-Katelijne-Waver", destination3.getCity()); 
		assertEquals(2860, destination3.getPostalCode(),0); 
		assertEquals("Mechelsesteenweg", destination3.getStreet());
		assertEquals(120, destination3.getStreetNumber(),0); 
		assertEquals("015 55 11 11", destination3.getContactInfo());
		assertEquals("Voedsel Depot: Den azalee", destination2.getDestinationName());
	
		assertEquals("drivemanagement must contain 2 drives  ",2, driveManagement.getDriveList(LocalDateTime.MIN, LocalDateTime.MAX).size());	
		
			
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void testSetEndTimeException() {
		drive1 = new DriveDTO();
		drive1.setStartTime(LocalDateTime.of(2019, 01, 01, 9, 30));
		drive1.setEndTime(LocalDateTime.of(2018, 01, 01, 10, 30));
	}

	@Test
	public void testRemoveDrive() throws Exception {
		testAddDrive();
		assertTrue(driveManagement.removeDrive(drive1.getId()));
		assertTrue(driveManagement.removeDrive(drive2.getId()));
		assertEquals(" there are 0: ",0, driveManagement.getDriveList(LocalDateTime.MIN, LocalDateTime.MAX).size());
		
		drive1 = new DriveDTO();	

		drive1.setStartTime(LocalDateTime.of(2019, 01, 01, 9, 30));
		drive1.setEndTime(LocalDateTime.of(2019, 01, 01, 10, 30));
		
		drive2 =new DriveDTO();
		drive2.setStartTime(LocalDateTime.of(2019, 02, 01, 9, 30));
		drive2.setEndTime(LocalDateTime.of(2019, 02, 01, 10, 30));
		
		drive3 =new DriveDTO();
		drive3.setStartTime(LocalDateTime.of(2019, 03, 01, 9, 30));
		drive3.setEndTime(LocalDateTime.of(2019, 03, 01, 10, 30));
		drive1 = driveManagement.addDrive(drive1);
		assertTrue(drive1.getId()!=null);
		drive2 = driveManagement.addDrive(drive2, destination1, destination3);
		assertTrue(drive2.getId()!=null);
		drive3 = driveManagement.addDrive(drive3, destination1, destination2);
		assertTrue(drive3.getId()!=null);
		assertEquals(" there are 3: ",3, driveManagement.getDriveList(LocalDateTime.MIN, LocalDateTime.MAX).size());
		
		assertTrue(driveManagement.removeDrive(drive3.getId()));
		assertEquals(" there are 2: ",2, driveManagement.getDriveList(LocalDateTime.MIN, LocalDateTime.MAX).size());	
	
	}



	@Test
	public void testGetDrivesByDestination() throws Exception {
		
		vzw.addUser(attendee);
		vzw.addUser(driver);
		vzw.addUser(depotHelp);
		
		drive1 = new DriveDTO();	
		drive1.setStartTime(LocalDateTime.of(2019, 01, 01, 9, 30));
		drive1.setEndTime(LocalDateTime.of(2019, 01, 01, 10, 30));
		
		drive2 =new DriveDTO();
		drive2.setStartTime(LocalDateTime.of(2019, 02, 01, 9, 30));
		drive2.setEndTime(LocalDateTime.of(2019, 02, 01, 10, 30));
		
		drive3 =new DriveDTO();
		drive3.setStartTime(LocalDateTime.of(2019, 03, 01, 9, 30));
		drive3.setEndTime(LocalDateTime.of(2019, 03, 01, 10, 30));
		drive1 =driveManagement.addDrive(drive1);
		drive2 =driveManagement.addDrive(drive2, destination1, destination3);
		drive3 = driveManagement.addDrive(drive3, destination1, destination2);
		
		assertEquals(" there are 3 drives total : ",3, driveManagement.getDriveList(LocalDateTime.MIN, LocalDateTime.MAX).size());

		assertEquals(" there are 2 drives for destination1: ",2, driveManagement.getDrivesByDestination(destination1).size());
		assertEquals(" there are  drives  for destination2: ",1, driveManagement.getDrivesByDestination(destination2).size());
		assertEquals(" there are 1 drives for destination3: ",1, driveManagement.getDrivesByDestination(destination3).size());
		destination1 = driveManagement.addDestination(drive1, destination1);
		destination2 = driveManagement.addDestination(drive1, destination2);
		destination3 = driveManagement.addDestination(drive1, destination3);
		List<DriveDTO> drives = (List<DriveDTO>) driveManagement.getDriveList(LocalDateTime.of(2019, 01, 01, 0, 0), LocalDateTime.of(2019, 01, 01, 23, 30));
		List<DestinationDTO> destinations = (List<DestinationDTO>) driveManagement.getDestinationsByDrive(drive1);
		assertEquals(drives.size(), 1);
		assertEquals("drive1 start time is not equal",LocalDateTime.of(2019, 01, 01, 9, 30), drives.get(0).getStartTime());
		assertEquals("drive 1 end time is not equal",LocalDateTime.of(2019, 01, 01, 10, 30), drives.get(0).getEndTime());
		assertEquals("drive 1 nr of destinations", destinations.size(), 3);
		assertEquals("drive destination 1 ", destination1.getId(), destinations.get(0).getId());
		assertEquals("drive destination 2 ", destination2.getId(), destinations.get(1).getId());
		assertEquals("drive destination 3 ", destination3.getId(), destinations.get(2).getId());
	}

	@Test
	public void testChangeDrive() throws Exception {
		
		testGetDrivesByDestination();
		
		assertEquals("user attendee has no  drives", driveManagement.getDrivesByDriver(attendee).size(),0,0);
		assertEquals("user driver has  no attendees", driveManagement.getDrivesByAttendee(driver).size(),0,0);
		List<DriveDTO> driverDrives = new ArrayList<> (driveManagement.getDrivesByDriver(driver));
		drive4 = driverDrives.get(0);
	
		drive4.setStartTime(LocalDateTime.of(2019, 03, 01, 9, 30));
		drive4.setEndTime(LocalDateTime.of(2019, 03, 01, 10, 30));
		

		assertEquals("user attendee has 1  drives", driveManagement.getDrivesByDriver(attendee).size(),1,0);
		assertEquals("user driver has  1 attendees", driveManagement.getDrivesByAttendee(driver).size(),1,0);
		
	}
	
	@Test 
	public void testAgreements() throws Exception {
		destination3 = driveManagement.addDestination(destination3);
		assertTrue(destination3.getId()!=null);
		AgreementDTO a1 = new AgreementDTO();
		a1.setAgreement("afspraak om 6u aan het depot");
		AgreementDTO a2 = new AgreementDTO();
		a2.setAgreement("Ophaal sleutel camionette + hek: in de sleutelkluis op de afgesproken plaats, code krijg je per sms, de avond voor de rit");
		a1 = driveManagement.addAgreement(destination3, a1);
		assertTrue(a1.getId()==1);
		a2 = driveManagement.addAgreement(destination3, a2);
		assertTrue(a2.getId()==2);
		List<AgreementDTO> agreements = driveManagement.getAgreements(destination3);
		assertEquals(a1, agreements.get(0));
		assertEquals(a2, agreements.get(1));
		//assertTrue(DriveManagement.removeAgreement(destination3, a1));
		//List<AgreementDTO> agreements = driveManagement.getAgreements(destination3);
		//assertEquals(a2, agreements.get(0));
		
	}
	
	@Test 
	public void testTasks() throws Exception {
		destination3 = driveManagement.addDestination(destination3);
		assertTrue(destination3.getId()!=null);
		TaskDTO t1 = new TaskDTO();
		t1.setTitle("Ophaling");
		t1.setDescription("Ophaal groenten op veiling");
		t1.setRole(Role.DRIVER);

		
		TaskDTO t2 = new TaskDTO();
		t2.setTitle("Toegangspasjes en ritplanning voor veiling");
		t2.setDescription("Bevinden zich in het rood kaft in de camionette.Gelieve te xchecken voor vertrek of deze aanwezig zijn");
		t2.setRole(Role.ATTENDEE);
		
		TaskDTO t3 = new TaskDTO();
		t3.setTitle("Klaarzetten voor ophaling  ");
		t3.setDescription("20 grijze bakken moeten worden voorzien.");
		t3.setRole(Role.DEPOTHELP);
		
		t1 = driveManagement.addTask(destination3,t1);
		t2 = driveManagement.addTask(destination3,t2);
		t3 = driveManagement.addTask(destination3,t3);
		
		List<TaskDTO> tasks = driveManagement.getTasks(destination3);
		
		assertEquals (t1,tasks.get(0));
		assertEquals (t2,tasks.get(1));
		assertEquals (t3,tasks.get(2));
	}
	
	
	
	
	@Before
	public void setUp() throws Exception {
		
		dependencyInjection();
		
		
		destination1.setCity("Sint-Niklaas");
		destination1.setPostalCode(9100);
		destination1.setStreet("LamStraat");
		destination1.setStreetNumber(113);
		destination1.setContactInfo("Els van de Steene:  03 780 52 35");
		destination1.setDestinationName("Vzw Voedsaam");
		
		
		destination2.setCity("Temse");
		destination2.setPostalCode(9140);
		destination2.setStreet("Krijgsbaan");
		destination2.setStreetNumber(247);
		destination2.setContactInfo("Els van de Steene:  03 780 52 35");
		destination2.setDestinationName("Voedsel Depot: Den azalee");	

		
		destination3.setCity("Sint-Katelijne-Waver");
		destination3.setPostalCode(2860 );
		destination3.setStreet("Mechelsesteenweg");
		destination3.setStreetNumber(120);
		destination3.setContactInfo("015 55 11 11");
		destination3.setDestinationName("Belorta");
		
		driver = new UserDTO("Kevin Van Leugenhaege");
		driver.setTel("0472 40 07 94");
		driver.setRole(Role.DRIVER);
		
		attendee = new UserDTO("Veerle Van Overtvelt");
		attendee.setTel("0497 16 36 26");
		attendee.setRole(Role.ATTENDEE);
		

		depotHelp = new UserDTO("Marie-No�lle Delarbre");
		depotHelp.setTel("0474 84 75 91");
		depotHelp.setRole(Role.DEPOTHELP);
		
		
	}
	

	@After
	public void tearDown() throws Exception {
		//userRepository.deleteAll(userRepository.getAll()); rollback exception
//		driveRepository.deleteAll(driveRepository.getAll());
//		userRepository.close();
//		driveRepository.close();
	}

}
