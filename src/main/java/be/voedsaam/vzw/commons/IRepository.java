package be.voedsaam.vzw.commons;

import java.util.Collection;
import java.util.List;

public interface IRepository<T> {
	public T find(T aggregate);
	public T create(T aggregate);
	
	public T update(T aggregate);
	
	public boolean delete(T aggregate);
	
	public boolean createAll(Collection<T> aggregates);
	
	public List<T> getAll();
	
	public boolean updateAll(Collection<T> aggregates);
	
	public boolean deleteAll(Collection<T> aggregates);

	public T getByID(Long id);
	
	public boolean exists(T aggregate);
	
	public void close();
}
