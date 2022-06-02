package ro.unibuc.FinalAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.FinalAssignment.model.Adresa;

import java.util.Optional;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Long>{

    //Optional<Adresa> findByEntry (String tara, String oras, String strada, int nrstrada);
}
