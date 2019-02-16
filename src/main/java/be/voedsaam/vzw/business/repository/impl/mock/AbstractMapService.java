package be.voedsaam.vzw.business.repository.impl.mock;



import be.voedsaam.vzw.business.DomainObject;
import be.voedsaam.vzw.commons.IRepository;

import java.util.*;

/**
 * Created by jt on 11/14/15.
 */
public abstract class AbstractMapService  {
    protected Map<Long, DomainObject> domainMap;

    public AbstractMapService() {
        domainMap = new HashMap<>();
    }

    public List<DomainObject> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public DomainObject getById(long id) {
        return domainMap.get(id);
    }

    public DomainObject saveOrUpdate(DomainObject domainObject) {
        if (domainObject != null){

            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(long id) {
        domainMap.remove(id);
    }

    private Long getNextKey(){
        return Long.valueOf(domainMap.keySet().size() + 1);
    }

}
