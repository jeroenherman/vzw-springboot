package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Contact;
import be.voedsaam.vzw.business.repository.ContactRepository;
import be.voedsaam.vzw.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class ContactServiceRepoImpl  implements ContactService {
    private ContactRepository contactRepository;
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<?> listAll() {
        List<Contact> contacts = new ArrayList<>();
         contactRepository.findAll().forEach(contacts::add);
         return contacts;
    }

    @Override
    public Contact getById(Long id) {
        Optional<Contact> o = contactRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }

    @Override
    public Contact saveOrUpdate(Contact domainObject) {
        return contactRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Contact> o = contactRepository.findById(id);
            if (o.isPresent())
                contactRepository.delete(o.get());
        }
    }
}

