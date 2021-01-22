package randevu.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ilac")
public class Ilac {

    private Long id;


    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String ad;
    @Basic
    @Column(name = "ad")
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return   ad  ;
    }
}
