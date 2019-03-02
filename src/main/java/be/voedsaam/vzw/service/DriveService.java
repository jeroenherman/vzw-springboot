package be.voedsaam.vzw.service;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.commons.CRUDService;

import java.util.List;

public interface DriveService extends CRUDService<Drive> {

    List<Drive> listAllByUser(String name);
    void deleteAllDrivesWithoutSchedule();
}
