package ro.unibuc.FinalAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.FinalAssignment.model.Stoc;

@Repository
public interface StocRepository extends JpaRepository<Stoc, Long> {

}
