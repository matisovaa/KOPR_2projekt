
package evidencia.dao;

import entity.PrezencnaListina;
import entity.Ucastnik;
import factory.Factory;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MySQLUcastnikDao implements UcastnikDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    public MySQLUcastnikDao(){
        jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
    }

    @Override
    public Long pridajUcastnika(String meno, String priezvisko) {
        
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("meno", meno)
                .addValue("priezvisko", priezvisko);

        Number id = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("ucastnik")
                .usingGeneratedKeyColumns("id")
                .usingColumns("meno","priezvisko")
                .executeAndReturnKey(parameters);
        
        return id.longValue();
    }

    @Override
    public List<Ucastnik> vratUcastnikov(PrezencnaListina prezencnaListina) {
        
        String sql = "SELECT id, meno, priezvisko FROM ucastnik JOIN ucast ON ucastnik.id = ucast.ucastnik_id WHERE prezencna_listina_id = ?";
        
        BeanPropertyRowMapper<Ucastnik> mapper = BeanPropertyRowMapper.newInstance(Ucastnik.class);        
        
        return jdbcTemplate.query(sql, mapper, prezencnaListina.getId());          
    }

    @Override
    public Ucastnik dajUcastnika(Long idUcastnika) {
        String sql = "SELECT * FROM ucastnik WHERE id = ?";

        try {
            BeanPropertyRowMapper<Ucastnik> mapper = BeanPropertyRowMapper.newInstance(Ucastnik.class);   
            return jdbcTemplate.queryForObject(sql, mapper, idUcastnika);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}
