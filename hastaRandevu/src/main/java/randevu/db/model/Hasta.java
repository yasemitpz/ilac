package randevu.db.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hasta")
public class Hasta extends Kisi {
    private Long id;


    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getAd()+" "+getSoyad();
    }
}


