package be.voedsaam.vzw.business;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Schedule extends AbstractDomainClass{
    private String name;
    @OneToMany
    private Collection<User> users = new ArrayList<>();
    @OneToMany
    private Collection<Drive> drives = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList((List<User>)users);
    }

    public List<Drive> getDrives() {
        return Collections.unmodifiableList((List<Drive>) drives);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addDrive(Drive drive){
        if(!drives.contains(drive)){
            drives.add(drive);
            drive.setSchedule(this);
        }
    }

        public void removeDrive(Drive drive){
        if(drives.contains(drive)){
            drives.remove(drive);
        }
    }
    public void addUser(User user){
        if(!users.contains(user)){
            users.add(user);
            user.setSchedule(this);
        }
    }

    public void removeUser(User user){
        if(users.contains(user)){
            users.remove(user);
        }
    }



}
