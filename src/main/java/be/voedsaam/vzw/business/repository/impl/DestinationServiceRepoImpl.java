package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class DestinationServiceRepoImpl implements DestinationService {

    private DestinationRepository destinationRepository;
    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<?> listAll() {
      List<Destination> destinations = new ArrayList<>();
        destinationRepository.findAll().forEach(destinations::add);
        return destinations;
    }

    @Override
    public Destination getById(Long id) {
      return destinationRepository.findById(id).get();
    }

    @Override
    public Destination saveOrUpdate(Destination domainObject) {
        return destinationRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        Optional<Destination> o =  destinationRepository.findById(id);
        if (o.isPresent())
            destinationRepository.delete(o.get());
    }
}
