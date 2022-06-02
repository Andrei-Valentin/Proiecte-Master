package ro.unibuc.FinalAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ro.unibuc.FinalAssignment.model.Produs;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;

public interface ProdusRepository extends JpaRepository<Produs, Long> {
    /*

    @Query(nativeQuery = true, value = "SELECT * FROM Produse ORDER BY pret ASC LIMIT 5")
    List<Produs> findTop5Cheap();

    Optional<Produs> findByDenumire(String denumire);


    @Transactional
    @Modifying
    @Query("DELETE FROM Produs p WHERE p.pret > :pret")
    void deleteProduseDupaPret(double pret);

     */


}



