package be.voedsaam.vzw.business;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Authority extends AbstractDomainClass implements GrantedAuthority{


    private String authority;
    @ManyToOne
    private User user;

    public Authority(){}
    public Authority(String authority){
        StringBuilder sb = new StringBuilder("ROLE_");
        sb.append(authority);
        this.authority = sb.toString();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority1 = (Authority) o;

        return getAuthority().equals(authority1.getAuthority());
    }

    @Override
    public int hashCode() {
        return getAuthority().hashCode();
    }
}
