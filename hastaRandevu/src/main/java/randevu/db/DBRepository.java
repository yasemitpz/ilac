package randevu.db;

import randevu.db.model.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class DBRepository {
    private EntityManager entityManager;
    public DBRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Kisi> getKisiler(){
        List<Kisi> kisiler = new ArrayList<>();

         kisiler.addAll(entityManager.createQuery("from Hasta ").getResultList());
         kisiler.addAll(entityManager.createQuery("from Doktor ").getResultList());

         return kisiler;
    }

    public List<Doktor> getDoktorlar(){
        return entityManager.createQuery("from Doktor ").getResultList();
    }

    public List<Hasta> getHastalar(){
        return entityManager.createQuery("from Hasta ").getResultList();
    }

    public List<Ilac> getIlaclar(){
        return entityManager.createQuery("from Ilac ").getResultList();
    }


    public List<RandevuZaman> getRandevuZamanlari(){
        return entityManager.createQuery("from RandevuZaman ").getResultList();
    }

    @Transactional
    public void randevuKaydet(RandevuZaman randevuZaman) {
        entityManager.getTransaction().begin();
        entityManager.persist(randevuZaman);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void hastaIlacKaydet(HastaIlac hastaIlac) {
        entityManager.getTransaction().begin();
        entityManager.persist(hastaIlac);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}