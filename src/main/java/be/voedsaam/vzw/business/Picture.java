package be.voedsaam.vzw.business;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Picture extends Link{
    private String alternateText;
    @Lob
    private byte[] data;
    public String getAlternateText() {
        return alternateText;
    }

    public void setAlternateText(String alternateText) {
        this.alternateText = alternateText;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
