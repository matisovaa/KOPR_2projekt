
package evidencia.dao;

import beforeAfter.BeforeAfterPreTesty;
import entity.PrezencnaListina;
import entity.Ucast;
import entity.Ucastnik;
import factory.Factory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import rowmapper.PrezencnaListinaRowMapper;

public class MySQLPrezencnaListinaDaoTest extends BeforeAfterPreTesty{
    
    private final PrezencnaListinaDao prezencnaListinaDao = Factory.INSTANCE.getPrezencnaListinaDao();
    private final JdbcTemplate jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
    
    /**
     * Test of pridajPrezencnuListinu method, of class MySQLPrezencnaListinaDao.
     */
    @Test
    public void testPridajPrezencnuListinu() {
        System.out.println("pridajPrezencnuListinu");
        
        Long idPredmetu = 3L;
        LocalDateTime datumACas = LocalDateTime.of(2017, 11, 25, 4, 18, 35);
        
        Long idPrezencnejListiny = prezencnaListinaDao.pridajPrezencnuListinu(idPredmetu, datumACas);
        
        String sql = "SELECT prezencna_listina.id, predmet_id, datum, nazov FROM prezencna_listina " +
                        "JOIN predmet ON prezencna_listina.predmet_id = predmet.id  WHERE prezencna_listina.id = 4";        
        PrezencnaListina p = jdbcTemplate.queryForObject(sql, new PrezencnaListinaRowMapper());
        
        assertEquals(new Long(4L), idPrezencnejListiny);
        assertEquals(new Long(3L), p.getPredmet().getId());
        assertEquals("Funkcionalne programovanie", p.getPredmet().getNazov()); 
        LocalDateTime ldt = LocalDateTime.ofInstant(p.getDatumACas().toInstant(), ZoneId.systemDefault());        
        assertEquals(LocalDateTime.of(2017, 11, 25, 4, 18, 35), ldt);
    }       
    
    /**
     * Test of zapisUcast method, of class MySQLPrezencnaListinaDao.
     */
    @Test
    public void testZapisUcast() {
        System.out.println("zapisUcast");
        
        Ucast ucast = new Ucast();
        ucast.setUcastnikId(3L);
        ucast.setPrezencnaListinaId(3L);
        
        prezencnaListinaDao.zapisUcast(ucast);        
        
        String sql = "SELECT * FROM ucast WHERE prezencna_listina_id = 3";
        BeanPropertyRowMapper<Ucast> mapper = BeanPropertyRowMapper.newInstance(Ucast.class);
        List<Ucast> ucasti = jdbcTemplate.query(sql, mapper);
        
        assertEquals(3, ucasti.size());        
    }
    
    /**
     * Test of vratPrezencneListiny method, of class MySQLPrezencnaListinaDao.
     */
    @Test
    public void testVratPrezencneListiny() {
        System.out.println("vratPrezencneListiny");
        
        Ucastnik u = new Ucastnik();
        u.setId(2L);
                
        List<PrezencnaListina> result = prezencnaListinaDao.vratPrezencneListiny(u);
        assertEquals(2, result.size());        
    }   

    /**
     * Test of dajPrezencnuListinu method, of class MySQLPrezencnaListinaDao.
     */
    @Test
    public void testDajPrezencnuListinu() {
        System.out.println("dajPrezencnuListinu");
        
        Long idPrezencnejListiny = 1L;        
        PrezencnaListina p = prezencnaListinaDao.dajPrezencnuListinu(idPrezencnejListiny);
        
        assertEquals(new Long(1L), idPrezencnejListiny);
        assertEquals(new Long(2L), p.getPredmet().getId());
        assertEquals("Diskretna matematika", p.getPredmet().getNazov()); 
        LocalDateTime ldt = LocalDateTime.ofInstant(p.getDatumACas().toInstant(), ZoneId.systemDefault());        
        assertEquals(LocalDateTime.of(2017, 11, 5, 14, 29, 36), ldt);
    }
    
}
