package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Contact;
import be.voedsaam.vzw.business.Task;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,Long> {

}
