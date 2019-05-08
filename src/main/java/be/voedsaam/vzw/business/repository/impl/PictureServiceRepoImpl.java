package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Picture;
import be.voedsaam.vzw.business.repository.PictureRepository;
import be.voedsaam.vzw.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class PictureServiceRepoImpl implements PictureService {

    private PictureRepository pictureRepository;
    @Autowired
    public void setPictureRepository(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<?> listAll() {
        List<Picture> tasks = new ArrayList<>();
        pictureRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Picture getById(Long id) {
        Optional<Picture> o = pictureRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }
    @Override
    public Picture saveOrUpdate(Picture domainObject) {
        return pictureRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Picture> o = pictureRepository.findById(id);
            if (o.isPresent())
                pictureRepository.delete(o.get());
        }
    }

}
