package be.voedsaam.vzw.business;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
public class Drive extends AbstractDomainClass {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "drives")
    private List<User> users;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {	CascadeType.MERGE})
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

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            user.addDrive(this);
        }
    }

    public void removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            user.removeDrive(this);
        }
    }
    public void clear(){
        for (int i = 0; i < users.size() ; i++) {
            users.get(i).removeDrive(this);
        }
        for (int i = 0; i <destinations.size() ; i++) {
            destinations.get(i).removeDrive(this);
        }
        if (schedule!=null)
        schedule.removeDrive(this);
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

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
