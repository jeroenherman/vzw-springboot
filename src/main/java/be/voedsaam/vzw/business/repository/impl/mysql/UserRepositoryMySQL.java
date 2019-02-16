package be.voedsaam.vzw.business.repository.impl.mysql;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.business.repository.impl.AbstractJpaDaoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@Profile("mysql")
public class UserRepositoryMySQL extends AbstractJpaDaoService implements UserRepository {



	public User find(User aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		User found = null;
			found = entityManager
					.createQuery("select u from User u where u.email = :email and u.password = :password"
							+ " or u.firstName= :firstName and u.lastName = :lastName", User.class)
					.setParameter("email", aggregate.getEmail())
					.setParameter("password", aggregate.getPassword())
					.setParameter("firstName", aggregate.getFirstName())
					.setParameter("lastName", aggregate.getLastName())
					.getSingleResult();
		entityManager.close();
		return found;
	}
	@Override
	public Optional<User> findByFullName(String fullName) {
		String[] name = fullName.split(" ");
		EntityManager entityManager = emf.createEntityManager();
		User found;
			found = entityManager
					.createQuery("select u from User u where  u.firstName= :firstName and u.lastName = :lastName",
							User.class)
					.setParameter("firstName", name[0])
					.setParameter("lastName", name[1])
					.getSingleResult();
		entityManager.close();
		return Optional.of(found);
	}


	@Override
	public void delete(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		em.close();
	}

	@Override
	public List<User> listAll() {
		EntityManager entityManager = emf.createEntityManager();
		List<User> found = entityManager.createQuery("select u from User u", User.class).getResultList();
		entityManager.close();
		return found;

	}

	@Override
	public User saveOrUpdate(User domainObject) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

//		if(domainObject.getPassword() != null){
//			domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
//		}

		User saveduser = em.merge(domainObject);
		em.getTransaction().commit();
		em.close();

		return saveduser;
	}

	@Override
	public User getById(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		User found = entityManager.find(User.class, id);
		entityManager.close();
		return found;
	}
}
