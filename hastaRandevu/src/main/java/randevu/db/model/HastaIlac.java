package randevu.db.model;

import javax.persistence.*;

@Entity
@Table(name = "hasta_ilac")
public class HastaIlac {

    private Long id;
    private Doktor doktor;
    private Hasta hasta;
    private Ilac ilac;

    @Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch= FetchType.LAZY , optional=false)
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

    @OneToOne
    @JoinColumn(name = "ilac_id", referencedColumnName = "id" )
    public Ilac getHastaIlac() {
        return ilac;
    }

    public void setHastaIlac(Ilac ilac) {
        this.ilac = ilac;
    }

    public HastaIlac() {
    }

    public HastaIlac(Doktor doktor, Hasta hasta, Ilac ilac) {
        this.doktor = doktor;
        this.hasta = hasta;
        this.ilac = ilac;
    }
}