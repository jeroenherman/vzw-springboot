package be.voedsaam.vzw.business.repository.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by jh on 11/02/19.
 */
public class AbstractJpaDaoService {

    protected EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
