package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.impl.mock.UserRepositoryMock;
import be.voedsaam.vzw.commons.Color;
import be.voedsaam.vzw.commons.Role;
import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.manager.VzwManagement;
import be.voedsaam.vzw.service.manager.impl.VzwManagementImpl;
import be.voedsaam.vzw.service.mapper.UserMapper;

public class Starter {

	public static void main(String[] args) {
		
	

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VZW_LOCAL");
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction et = em.getTransaction();
//		et.begin();	
		
		Address a1 = new Address("Sportlaan",33,9170,"Sint-Niklaas");
		User jeroen = new User("jeroen", "herman","jeroen.herman@voedsaam.be", "037797243",a1, Role.COORDINATOR, Color.RED);
		jeroen.setPassword("Test123");
		User logistics = new User("Cindy","DePuydt","Cindy.DePuydt@voedsaam.be", "03 /780.52.35");
		logistics.setRole(Role.LOGISTICS);
		logistics.setPassword("Test123");
		User coordinator  = new User("Els", "VandeSteene", "Els.VandeSteene@voedsaam.be","0492/250641");
		coordinator.setRole(Role.COORDINATOR);
		coordinator.setPassword("Test123");
		User partner = new User("Kathy","blomme", "kathy.blomme@gmail.com", "unknown");
		partner.setRole(Role.PARTNER);
		User volunteer = new User("leonard", "cleys", "cleysveedee@telenet.be", "unknown");
		volunteer.setRole(Role.VOLUNTEER);
		volunteer.setColor(Color.WHITE);
		volunteer.setPassword("Test123");
//		em.persist(a1);
//		em.persist(jeroen);
//		em.persist(volunteer);
//		em.persist(coordinator);
//		em.persist(logistics);
//		
//		et.commit();
//		em.close();
		
//		EntityManager em2 = emf.createEntityManager();
//		EntityTransaction et2 = em2.getTransaction();
//		et2.begin();
//	
//		System.out.println("persisted");
//		et2.commit();
//		em2.close();
//		
//		
//		emf.close();
//		
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("test");
		userDTO.setLastName("test");
		userDTO.setEmail("test.test@voedsaam.be");
		userDTO.setPassword("Test123");
		
		VzwManagement vzw = new VzwManagementImpl(new UserMapper(), new UserRepositoryMock());
		UserMapper userMapper = new UserMapper();
		vzw.addUser(userDTO);
		vzw.addUser(userMapper.mapToDTO(jeroen));
		vzw.addUser(userMapper.mapToDTO(volunteer));
		vzw.addUser(userMapper.mapToDTO(partner));
		vzw.addUser(userMapper.mapToDTO(coordinator));
		vzw.addUser(userMapper.mapToDTO(logistics));
		
		System.out.println("All Users: "+vzw.getAllUsers());
		
		System.out.println("All Coordinators: ");
		vzw.getAllUsers().stream().filter(u -> u.getRole()!=null).filter(u -> u.getRole().equals(Role.COORDINATOR)).forEach(System.out::println);	
		
	}

}