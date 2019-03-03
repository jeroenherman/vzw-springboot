package be.voedsaam.vzw.business.impl;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;

import javax.persistence.Entity;

@Entity
public class Partner extends User {

    public Partner() {
        setRole(Role.PARTNER);
    }

    public Partner(String fullName) {
        super(fullName);
    }

    public Partner(String firstName, String lastName, String email, String tel) {
        super(firstName, lastName, email, tel);
    }

    @Override
    public void setRole(Role role) {
        super.setRole(Role.PARTNER);
    }
}
