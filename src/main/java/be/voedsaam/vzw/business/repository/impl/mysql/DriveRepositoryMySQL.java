package be.voedsaam.vzw.business.repository.impl.mysql;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.impl.AbstractJpaDaoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("mysql")
public class DriveRepositoryMySQL extends AbstractJpaDaoService implements DriveRepository {

	@Override
	public void delete(Long id) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Drive.class, id));
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Drive> listAll() {
		EntityManager entityManager = emf.createEntityManager();
		List<Drive> found =entityManager.createQuery("select d from Drive d", Drive.class).getResultList();
		entityManager.close();
		return found;
	}

	@Override
	public Drive getById(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		Drive found = entityManager.find(Drive.class, id);
		entityManager.close();
		return found;
	}
	@Override
	public Drive saveOrUpdate(Drive domainObject) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Drive savedDrive = em.merge(domainObject);
		em.getTransaction().commit();
		em.close();
		return savedDrive;
	}

}
