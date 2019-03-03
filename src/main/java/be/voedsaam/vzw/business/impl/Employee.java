package be.voedsaam.vzw.business.impl;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
public class Employee extends User {
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_SCHEDULE",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "schedule_id")})
    private List<Schedule> schedules = new ArrayList<>();

    public Employee(){

    }


    public Employee(String fn, String ln, String email, String tel, Address a1, Role r, Color c) {
        super( fn,  ln,  email,  tel,  a1,  r,  c);
    }

    public Employee(String fn, String ln, String email, String tel) {
        super(fn,ln,email,tel);
    }

    public void addSchedule(Schedule schedule) {
        if (!schedules.contains(schedule)) {
            this.schedules.add(schedule);
            schedule.addUser(this);
        }
    }

    public void removeSchedule(Schedule schedule) {
        if (schedules.contains(schedule)) {
            this.schedules.remove(schedule);
            schedule.removeUser(this);
        }
    }

    public List<Schedule> getSchedules() {
        return Collections.unmodifiableList(schedules);
    }
    @PreRemove
    public void clear() {
        schedules.clear();
    }
}
