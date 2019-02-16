package be.voedsaam.vzw.commons;

import java.util.Collection;
import java.util.List;

public interface IRepository<T> {

	List<?> listAll();

	T getById(Long id);

	T saveOrUpdate(T domainObject);

	void delete(Long id);

}
