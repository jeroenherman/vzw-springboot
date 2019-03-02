package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.Role;

import javax.persistence.*;
import java.util.*;

@Entity
public class Schedule extends AbstractDomainClass {
    private String name;
    @ManyToMany( cascade = {CascadeType.MERGE, CascadeType.PERSIST} , mappedBy = "schedules")
    private Collection<Employee> users = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Drive> drives = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Employee> getUsers() {
        return Collections.unmodifiableList((List<Employee>) users);
    }

    public List<Drive> getDrives() {
        return Collections.unmodifiableList((List<Drive>) drives);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDrive(Drive drive) {
        if (!drives.contains(drive)) {
            drives.add(drive);
            drive.setSchedule(this);
        }
    }

    public void removeDrive(Drive drive) {
        if (drives.contains(drive)) {
            drives.remove(drive);
            drive.setSchedule(null);
        }
    }

    public void addUser(Employee user) throws UnsupportedOperationException {
        if (!users.contains(user)) {
            if (user.getRole().equals(Role.COORDINATOR)) {
                for (User u : users) {
                    if (u.getRole().equals(Role.COORDINATOR))
                        throw new UnsupportedOperationException("Schedule may have only one Coordinator");
                }
            }
            users.add(user);
            user.addSchedule(this);
        }
    }

    public void removeUser(Employee user) {
        if (users.contains(user)) {
            users.remove(user);
            user.removeSchedule(this);
        }
    }

    public void removeDrives(){
        for (Iterator<Drive> iterator =  drives.iterator(); iterator.hasNext();) {
            Drive drive = iterator.next();
            drive.clear();
        }
        drives.clear();
    }
    @PreRemove
    public void clear(){
        users.clear();
        drives.clear();
    }
}
