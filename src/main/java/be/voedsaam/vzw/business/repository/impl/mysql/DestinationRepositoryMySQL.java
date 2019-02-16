package be.voedsaam.vzw.business.repository.impl.mysql;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.business.repository.impl.AbstractJpaDaoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
@Service
@Profile("mysql")
public class DestinationRepositoryMySQL extends AbstractJpaDaoService implements DestinationRepository {
    @Override
    public List<Destination> listAll() {
        EntityManager entityManager = emf.createEntityManager();
        List<Destination> found =entityManager.createQuery("select d from Destination d", Destination.class).getResultList();
        entityManager.close();
        return found;
    }

    @Override
    public Destination getById(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        Destination found = entityManager.find(Destination.class, id);
        entityManager.close();
        return found;
    }

    @Override
    public Destination saveOrUpdate(Destination domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Destination savedDrive = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return savedDrive;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Destination.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
