package be.voedsaam.vzw.business.repository.impl.mysql;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.impl.AbstractJpaDaoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collection;
import java.util.List;

@Service
@Profile("mysql")
public class DriveRepositoryMySQL extends AbstractJpaDaoService implements DriveRepository {
	
	
	@Override
	public Drive create(Drive aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		if(aggregate.getDriver()!=null)
		aggregate.setDriver(entityManager.merge(aggregate.getDriver()));
		if(aggregate.getAttendee()!=null)
		aggregate.setAttendee(entityManager.merge(aggregate.getAttendee()));
		if(aggregate.getDepotHelp()!=null)
		aggregate.setDepotHelp(entityManager.merge(aggregate.getDepotHelp()));		
		for (Destination destination : aggregate.getDestinations()) {
			destination = findDestinationById(destination.getId());
		}
		aggregate = entityManager.merge(aggregate);
		entityTransaction.commit();
		return aggregate;
		}
	

	@Override
	public Drive update(Drive aggregate) {
		return create(aggregate);
	}

	@Override
	public boolean delete(Drive aggregate) {
		EntityManager entityManager = emf.createEntityManager();
		Drive drive = find(aggregate);
		entityManager.getTransaction().begin();
		entityManager.remove(drive);
		entityManager.getTransaction().commit();
		return !exists(aggregate);
	}

	@Override
	public boolean delete(long id) {
		boolean result = false;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Drive.class, id));
		if (em.find(Drive.class,id)==null)
			result = true;
		em.getTransaction().commit();
		return result;
	}

	@Override
	public boolean createAll(Collection<Drive> aggregates) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(aggregates);
		entityTransaction.commit();
		return true;
	}
	
	

	@Override
	public List<Drive> getAll() {
		EntityManager entityManager = emf.createEntityManager();
		return entityManager.createQuery("select d from Drive d", Drive.class).getResultList();
	}

	@Override
	public boolean updateAll(Collection<Drive> aggregates) {
		aggregates.forEach(a -> update(a));
		return true;
	}

	@Override
	public boolean deleteAll(Collection<Drive> aggregates) {
		aggregates.forEach(a-> delete(a));
		return false;
	}

	@Override
	public Drive getByID(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		return entityManager.find(Drive.class, id);
	}

	@Override
	public Drive find(Drive aggregate) {
		if (aggregate.getId()!=null)
		return getByID(aggregate.getId());
		return null;
		
	}


	@Override
	public boolean exists(Drive aggregate) {
		if (aggregate.getId()==null)
			return false;
		 return getByID(aggregate.getId())!=null;
	}

	@Override
	public void close() {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.close();
		emf.close();
	}

	@Override
	public Destination findDestinationById(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		if(id !=null)
		return entityManager.find(Destination.class, id);
		return null;
	}

	@Override
	public Destination addDestination(Destination destination) {
		EntityManager entityManager = emf.createEntityManager();
		Destination found = findDestinationById(destination.getId());
		if (found!=null)
		return found;
		return entityManager.merge(destination);
		
	}



}
