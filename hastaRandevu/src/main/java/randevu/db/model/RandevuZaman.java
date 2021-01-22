package randevu.db.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "randevu_zaman", schema = "randevu")
public class RandevuZaman {
    private Long id;
    private Timestamp zaman;
    private Doktor doktor;
    private Hasta hasta;

    public RandevuZaman() {
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "zaman")
    public Timestamp getZaman() {
        return zaman;
    }







    public RandevuZaman(Timestamp zaman, Doktor doktor, Hasta hasta) {
        this.zaman = zaman;
        this.doktor = doktor;
        this.hasta = hasta;
    }

    public void setZaman(Timestamp zaman) {
        this.zaman = zaman;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandevuZaman that = (RandevuZaman) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(zaman, that.zaman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zaman);
    }

    @OneToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="doktor_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    @OneToOne
    @JoinColumn(name = "hasta_id", referencedColumnName = "id" )
    public Hasta getHasta() {
        return hasta;
    }

    public void setHasta(Hasta hasta) {
        this.hasta = hasta;
    }

}
