package randevu.db.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doktor")
public class Doktor extends Kisi{
    private Long id;

    private String unvan;

    @Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "unvan")
    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    @Override
    public String toString() {
        return getAd()+" "+getSoyad();
    }
}
