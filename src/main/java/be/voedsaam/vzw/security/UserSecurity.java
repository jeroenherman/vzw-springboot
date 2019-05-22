package be.voedsaam.vzw.security;

import be.voedsaam.vzw.business.Authority;
import be.voedsaam.vzw.business.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurity extends User implements UserDetails {

    public UserSecurity(){}

    public UserSecurity(User user) {
        this.setId(user.getId());
        this.setPassword(user.getEncryptedPassword());
        this.setTel(user.getTel());
        this.setRole(user.getRole());
        this.setColor(user.getColor());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setAddress(user.getAddress());
        this.setEmail(user.getEmail());
        this.getAuthorities().add(new Authority(user.getRole().getValue()));

    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
