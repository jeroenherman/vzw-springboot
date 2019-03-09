package be.voedsaam.vzw.service;

import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.commons.CRUDService;

import java.util.List;

public interface ScheduleService extends CRUDService<Schedule> {

    List<Schedule> listAllByUserName(String userName);
    void removeDrives(Integer id);
    List<Schedule> listAllOrphans();
}
