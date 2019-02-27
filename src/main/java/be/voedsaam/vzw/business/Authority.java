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

    @Override
    public String toString() {
        return
                "authority='" + authority + '\'' +
                '}';
    }
}
