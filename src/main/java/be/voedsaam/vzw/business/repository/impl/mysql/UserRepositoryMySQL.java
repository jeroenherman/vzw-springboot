package be.voedsaam.vzw.business.repository.impl.mysql;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.business.repository.impl.AbstractJpaDaoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;
@Service
@Profile("mysql")
public class UserRepositoryMySQL extends AbstractJpaDaoService implements UserRepository {

	



	public void close() {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.close();
		emf.close();
	}

	@Override
	public User create(User aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(aggregate);
		entityTransaction.commit();
		entityManager.close();
		return aggregate;
	}

	@Override
	public User find(User aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		User found = null;
		if (exists(aggregate)) {

			found = entityManager
					.createQuery("select u from User u where u.email = :email and u.password = :password"
							+ " or u.firstName= :firstName and u.lastName = :lastName", User.class)
					.setParameter("email", aggregate.getEmail())
					.setParameter("password", aggregate.getPassword())
					.setParameter("firstName", aggregate.getFirstName())
					.setParameter("lastName", aggregate.getLastName())
					.getSingleResult();

		}
		entityManager.close();
		return found;
	}

	public User findByName(User aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		User found = null;
		if (exists(aggregate)) {

			found = entityManager
					.createQuery("select u from User u where  u.firstName= :firstName and u.lastName = :lastName",
							User.class)
					.setParameter("firstName", aggregate.getFirstName())
					.setParameter("lastName", aggregate.getLastName()).getSingleResult();

		}
		entityManager.close();
		return found;
	}

	@Override
	public User update(User aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		User update = find(aggregate);
		entityManager.getTransaction().begin();
		update = aggregate;
		entityManager.getTransaction().commit();
		entityManager.close();
		return update;
	}

	@Override
	public boolean delete(User aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		User user = find(aggregate);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public boolean delete(long id) {
		boolean result = false;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		if (em.find(User.class,id)==null)
			result = true;
		em.getTransaction().commit();
		em.close();
		return result;
	}

	@Override
	public boolean createAll(Collection<User> aggregates) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(aggregates);
		entityTransaction.commit();
		entityManager.close();
		return true;
	}

	@Override
	public List<User> getAll() {
		EntityManager entityManager = emf.createEntityManager();
		List<User> found = entityManager.createQuery("select u from User u", User.class).getResultList();
		entityManager.close();
		return found;

	}

	@Override
	public boolean updateAll(Collection<User> aggregates) {
		aggregates.forEach(a -> update(a));
		return true;
	}

	@Override
	public boolean deleteAll(Collection<User> aggregates) {
		aggregates.forEach(a -> delete(a));
		return true;
	}

	@Override
	public User getByID(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		User found = entityManager.find(User.class, id);
		return found;
	}

	@Override
	public boolean exists(User aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		Long count = (Long) entityManager
				.createQuery("select count(u) from User u where u.email = :email and u.password = :password "
						+ "or u.firstName= :firstName and u.lastName = :lastName")
				.setParameter("email", aggregate.getEmail())
				.setParameter("password", aggregate.getPassword())
				.setParameter("firstName", aggregate.getFirstName())
				.setParameter("lastName", aggregate.getLastName())
				.getSingleResult();
		entityManager.close();
		return ((count.equals(0L)) ? false : true);
	}

}
