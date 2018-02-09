
package evidencia.dao;

import beforeAfter.BeforeAfterPreTesty;
import factory.Factory;
import entity.Predmet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySQLPredmetDaoTest extends BeforeAfterPreTesty{
    
    private final PredmetDao predmetDao = Factory.INSTANCE.getPredmetDao();
    private final JdbcTemplate jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
    
    /**
     * Test of pridajPredmet method, of class MySQLPredmetDao.
     */
    @Test
    public void testPridajPredmet() {
        System.out.println("pridajPredmet");
        
        String nazovPredmetu = "Databázy";        
        Long result = predmetDao.pridajPredmet(nazovPredmetu);
        
        String sql = "SELECT * FROM predmet WHERE id = 4";
        
        BeanPropertyRowMapper<Predmet> mapper = BeanPropertyRowMapper.newInstance(Predmet.class);
        Predmet p = jdbcTemplate.queryForObject(sql, mapper);
        
        assertEquals("Databázy", p.getNazov()); 
        
        assertEquals(new Long(4), result);        
    }

    /**
     * Test of dajPredmet method, of class MySQLPredmetDao.
     */
    @Test
    public void testDajPredmet() {
        System.out.println("dajPredmet");
        
        Long idPredmetu = 3L;
        
        Predmet p = predmetDao.dajPredmet(idPredmetu);        
        
        assertEquals(new Long(3L), p.getId());
        assertEquals("Funkcionalne programovanie", p.getNazov());
    }
    
}
