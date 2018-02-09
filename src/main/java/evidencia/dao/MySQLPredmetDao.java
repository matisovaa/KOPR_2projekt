
package evidencia.dao;

import entity.Predmet;
import factory.Factory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MySQLPredmetDao implements PredmetDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    public MySQLPredmetDao(){
        jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
    }

    @Override
    public Long pridajPredmet(String nazovPredmetu) {
        
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("nazov", nazovPredmetu);

        Number id = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("predmet")
                .usingGeneratedKeyColumns("id")
                .usingColumns("nazov")
                .executeAndReturnKey(parameters);
        
        return id.longValue();
    }

    @Override
    public Predmet dajPredmet(Long idPredmetu) {
        String sql = "SELECT * FROM predmet WHERE id = ?";

        try {
            BeanPropertyRowMapper<Predmet> mapper = BeanPropertyRowMapper.newInstance(Predmet.class);
            return jdbcTemplate.queryForObject(sql, mapper, idPredmetu);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}
