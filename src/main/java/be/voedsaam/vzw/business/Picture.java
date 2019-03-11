package be.voedsaam.vzw.business;

import javax.persistence.Entity;

@Entity
public class Picture extends Link{
    private String alternateText;

    public String getAlternateText() {
        return alternateText;
    }

    public void setAlternateText(String alternateText) {
        this.alternateText = alternateText;
    }
}
