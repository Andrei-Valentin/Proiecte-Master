package ro.unibuc.FinalAssignment.repository;
/*
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.unibuc.FinalAssignment.model.Categorie;
import ro.unibuc.FinalAssignment.model.Produs;

import java.util.List;
import java.util.UUID;

@Repository
public class ProdusRepositoryImpl implements ProdusRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProdusRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Produs insert(Produs produs) {
        produs.setId(UUID.randomUUID().toString());

        jdbcTemplate.update("insert into plays values (?, ?, ?, ?)",
                produs.getId(), produs.getDenumire(), produs.getCategorie().toString(), produs.getPret().toString());

        return produs;
    }

    @Override
    public void delete(String idProdus) {
        jdbcTemplate.update("delete from produse where id = ?", idProdus);
    }

    @Override
    public List<Produs> findByMagazinId(String idMagazin) {
        return jdbcTemplate.query("select * from produse where magazin_id = ?", (rs, rowNo) -> {
            final var mapped = new Produs();
            mapped.setId(rs.getString("id"));
            mapped.setDenumire(rs.getString("Denumire"));
            mapped.setCategorie(Categorie.valueOf(rs.getString("Categorie")));
            mapped.setPret(Integer.valueOf(rs.getString("Pret")));

            return mapped;
        }, idMagazin);
    }
}


 */