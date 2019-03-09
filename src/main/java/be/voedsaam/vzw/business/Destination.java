package be.voedsaam.vzw.business;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Destination extends AbstractDomainClass {
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    @Lob
    @ElementCollection
    private List<String> agreements;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "destination")
    private Collection<Task> tasks;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "destinations")
    private Collection<Drive> drives;
    private String contactInfo;
    private String destinationName;

    public Destination() {
        drives = new ArrayList<>();
        agreements = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getAgreements() {
        return Collections.unmodifiableList(agreements);
    }

    public void setAgreements(List<String> agreements) {
        this.agreements = agreements;
    }

    public void addAgreement(String agreement) {
        agreements.add(agreement);
    }

    public void removeAgreement(String agreement) {
        agreements.remove(agreement);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList((List<Task>) tasks);
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Drive> getDrives() {
        return Collections.unmodifiableList((List<Drive>) drives);
    }

    public void addDrive(Drive drive) {
        if (!drives.contains(drive)) {
            drives.add(drive);
            drive.addDestination(this);
        }
    }

    public void removeDrive(Drive drive) {
        if (drives.contains(drive)) {
            drives.remove(drive);
            drive.removeDestination(this);
        }
    }

    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
            task.setDestination(this);
        }
    }

    public void removeTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
        }
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Destination that = (Destination) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return destinationName.equals(that.destinationName);
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + destinationName.hashCode();
        return result;
    }
}
