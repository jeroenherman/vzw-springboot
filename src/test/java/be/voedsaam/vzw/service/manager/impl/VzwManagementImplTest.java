package be.voedsaam.vzw.service.manager.impl;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.business.repository.impl.mysql.UserRepositoryMySQL;
import be.voedsaam.vzw.commons.Color;
import be.voedsaam.vzw.commons.Role;
import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.manager.VzwManagement;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VzwManagementImplTest {
	private User jeroen;
	private User coordinator;
	private User logistics;
	private User volunteer;
	private User partner;
	private UserDTO userDTO;
	private Address a1;
	private VzwManagement vzw;
	private UserMapper userMapper;
	private UserRepository userRepository;
	
	private void dependencyInjection() {
	
		userRepository = new UserRepositoryMySQL();
		userMapper = new UserMapper();	
		vzw = new VzwManagementImpl(userMapper,userRepository);
		
	}
	
	@Before
	public void setUp() throws Exception {
		dependencyInjection();
		
		a1 = new Address("Sportlaan",33,9170,"Sint-Niklaas");
		jeroen = new User("jeroen", "herman","jeroen.herman@voedsaam.be", "037797243",a1, Role.COORDINATOR, Color.RED);
		logistics = new User("Cindy","DePuydt","Cindy.DePuydt@voedsaam.be", "03 /780.52.35");
		logistics.setRole(Role.LOGISTICS);
		coordinator  = new User("Els", "VandeSteene", "Els.VandeSteene@voedsaam.be","0492/250641");
		coordinator.setRole(Role.COORDINATOR);
		partner = new User("Kathy","blomme", "kathy.blomme@gmail.com", "unknown");
		partner.setRole(Role.PARTNER);
		volunteer = new User("leonard", "cleys", "cleysveedee@telenet.be", "unknown");
		volunteer.setRole(Role.VOLUNTEER);
		volunteer.setColor(Color.WHITE);
		jeroen.setPassword("Test123");
		logistics.setPassword("Test123");
		volunteer.setPassword("Test123");
		coordinator.setPassword("Test123");
		
		
	
	}

	@After
	public void tearDown() throws Exception {
		//userRepository.deleteAll(userRepository.getAll());
		//userRepository.close();	
	}

	@Test
	public void testAddUser()  {
		userDTO = new UserDTO();
		userDTO.setFirstName("test");
		userDTO.setLastName("test");
		userDTO.setEmail("test.test@voedsaam.be");
		userDTO.setPassword("Test123");
		assertEquals("UserDto are not equal",  userDTO, userDTO);
		assertEquals("Users  are not equal",  userMapper.mapToObj(userDTO), userMapper.mapToObj(userDTO));
		assertEquals("user not created",userDTO,vzw.addUser(userDTO));
		assertEquals("user not created",userDTO,vzw.addUser(userDTO));
		assertEquals("Only one user present",1,vzw.getAllUsers().size(),0);
		
		
	}

	@Test
	public void testLogin() {
		testAddUser();
		assertEquals("user not logged in", userDTO, vzw.login(userDTO) );
		assertEquals(userDTO.getEmail(), vzw.getCurrentUser().getEmail());
		assertEquals(userDTO.getFirstName(), vzw.getCurrentUser().getFirstName());
		assertEquals(userDTO.getLastName(), vzw.getCurrentUser().getLastName());
		assertEquals("User not equal to logged in user ", userDTO, vzw.getCurrentUser());
	}

	@Test
	public void testLogOut()  {
		testLogin();
		
		assertTrue("user not logged out", vzw.logOut());
		assertTrue("current user not null", vzw.getCurrentUser()==null);
		
	}

	@Test
	public void testRemoveUser()  {
		testAddUser();
		assertTrue("user not removed",vzw.removeUser(userDTO));
		assertEquals("user is logged in",null, vzw.login(userDTO));
		assertTrue("current user not null", vzw.getCurrentUser()==null);
		
	}

	@Test
	public void testGetAllUsers()  {
		vzw.addUser(userMapper.mapToDTO(jeroen));
		vzw.addUser(userMapper.mapToDTO(volunteer));
		vzw.addUser(userMapper.mapToDTO(logistics));
		vzw.addUser(userMapper.mapToDTO(coordinator));
		assertEquals("collection size not 4", 4, vzw.getAllUsers().size(),0);
		userDTO = new UserDTO();
		userDTO.setFirstName("test2");
		userDTO.setLastName("test2");
		userDTO.setEmail("test2.test@voedsaam.be");
		userDTO.setPassword("Test123");
		vzw.addUser(userDTO);
		assertEquals("collection size not 5", vzw.getAllUsers().size(),5,0);		
	}
	
	
	

}
