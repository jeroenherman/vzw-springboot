package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriveRepository extends CrudRepository<Drive, Long>{

    List<Drive> findAllByUsersContaining(User user);

}
