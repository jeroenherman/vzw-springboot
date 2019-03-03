package be.voedsaam.vzw.business.impl;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Role;

import javax.persistence.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Entity
public class Volunteer extends User {
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "users")

    private List<Drive> drives = new CopyOnWriteArrayList<>();

    public Volunteer(){
        setRole(Role.VOLUNTEER);
    }

    public Volunteer(String fullName) {
        super(fullName);
    }

    public Volunteer(String fn, String ln, String email, String tel) {
        super(fn,ln,email,tel);
    }

    public void addDrive(Drive drive) {
        if (!drives.contains(drive)) {
            drives.add(drive);
            drive.addUser(this);
        }
    }

    public void removeDrive(Drive drive) {
        if (drives.contains(drive)) {
            drive.removeUser(this);
            drives.remove(drive);
        }
    }

    public List<Drive> getDrives() {
        return Collections.unmodifiableList(drives);
    }

    @Override
    public void setRole(Role role) {
        if (role.equals(Role.COORDINATOR)||role.equals(Role.LOGISTICS))
        super.setRole(Role.VOLUNTEER);
        else
        super.setRole(role);
    }
}
