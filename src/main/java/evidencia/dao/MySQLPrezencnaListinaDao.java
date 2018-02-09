
package evidencia.dao;

import entity.PrezencnaListina;
import entity.Ucast;
import entity.Ucastnik;
import factory.Factory;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import rowmapper.PrezencnaListinaRowMapper;

public class MySQLPrezencnaListinaDao implements PrezencnaListinaDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    public MySQLPrezencnaListinaDao(){
        jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
    }

    @Override
    public Long pridajPrezencnuListinu(Long idPredmetu, LocalDateTime datumACas) {
        
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("predmet_id", idPredmetu)
                .addValue("datum", datumACas);

        Number id = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("prezencna_listina")
                .usingGeneratedKeyColumns("id")
                .usingColumns("predmet_id","datum")
                .executeAndReturnKey(parameters);       
        
        return id.longValue();
    }
    
    @Override
    public void zapisUcast(Ucast ucast) {
        String sql = "INSERT INTO ucast (ucastnik_id, prezencna_listina_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, ucast.getUcastnikId(), ucast.getPrezencnaListinaId());
    }
    
    @Override
    public List<PrezencnaListina> vratPrezencneListiny(Ucastnik ucastnik) {
        String sql = "SELECT prezencna_listina.id, predmet_id, datum, nazov FROM prezencna_listina JOIN ucast ON prezencna_listina.id = ucast.prezencna_listina_id " +
            "JOIN predmet ON prezencna_listina.predmet_id = predmet.id  WHERE ucastnik_id = ?";        
        return jdbcTemplate.query(sql, new PrezencnaListinaRowMapper(), ucastnik.getId());
    }     

    @Override
    public PrezencnaListina dajPrezencnuListinu(Long idPrezencnejListiny) {
        String sql = "SELECT prezencna_listina.id, predmet_id, datum, nazov FROM prezencna_listina JOIN predmet ON prezencna_listina.predmet_id = predmet.id  "
                + "WHERE prezencna_listina.id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new PrezencnaListinaRowMapper(), idPrezencnejListiny);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
