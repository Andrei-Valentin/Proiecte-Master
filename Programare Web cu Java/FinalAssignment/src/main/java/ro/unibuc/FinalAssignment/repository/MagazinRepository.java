package ro.unibuc.FinalAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.FinalAssignment.model.Magazin;

@Repository
public interface MagazinRepository extends JpaRepository<Magazin, Long> {

}
