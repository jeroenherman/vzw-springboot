package be.voedsaam.vzw.business;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
public class Drive extends AbstractDomainClass {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "DRIVE_VOLUNTEER",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "drive_id")})
    private List<Volunteer> users;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {	CascadeType.ALL})
    @JoinTable(name = "DRIVE_DESTINATION",
            joinColumns = { @JoinColumn(name = "drive_id") },
            inverseJoinColumns = { @JoinColumn(name = "destination_id") })
    private List<Destination> destinations;
    @ManyToOne()
    private Schedule schedule;

    public Drive() {
        super();
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
        users = new CopyOnWriteArrayList<>();
        destinations = new ArrayList<>();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) throws ArithmeticException {
        if (endTime.isBefore(startTime))
            throw new ArithmeticException("End time must be greater then start time");
        this.endTime = endTime;
    }

    public List<Destination> getDestinations() {
        return Collections.unmodifiableList(destinations);
    }

    public void addDestination(Destination destination) {
        if (!destinations.contains(destination)) {
            destinations.add(destination);
            destination.addDrive(this);
        }
    }

    public void removeDestination(Destination destination) {
        if (destinations.contains(destination)) {
            destinations.remove(destination);
            destination.removeDrive(this);
        }
    }

    public List<Volunteer> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addUser(Volunteer user) {
        if (!users.contains(user)) {
            users.add(user);
            user.addDrive(this);
        }
    }

    public void removeUser(Volunteer user) {
        if (users.contains(user)) {
            users.remove(user);
            user.removeDrive(this);
        }
    }
    @PreRemove
    public void clear(){
        users.clear();
        destinations.clear();
        setSchedule(null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void removeUsers() {
        for (Volunteer u: users) {
            u.removeDrive(this);
        }
        users.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drive drive = (Drive) o;

        if (!getDescription().equals(drive.getDescription())) return false;
        if (!getStartTime().equals(drive.getStartTime())) return false;
        return getEndTime().equals(drive.getEndTime());
    }

    @Override
    public int hashCode() {
        int result = getDescription().hashCode();
        result = 31 * result + getStartTime().hashCode();
        result = 31 * result + getEndTime().hashCode();
        return result;
    }
}
