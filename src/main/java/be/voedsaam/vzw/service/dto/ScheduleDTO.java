package be.voedsaam.vzw.service.dto;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDTO {
    private Long id;
    private String name;
    private String owner;
    private List<String> viewers =new ArrayList<>();
    private List<String> drives = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getViewers() {
        return viewers;
    }

    public void setViewers(List<String> users) {
        this.viewers = users;
    }

    public List<String> getDrives() {
        return drives;
    }

    public void setDrives(List<String> drives) {
        this.drives = drives;
    }
}
