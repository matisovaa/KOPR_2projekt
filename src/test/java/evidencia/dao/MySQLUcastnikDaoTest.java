
package evidencia.dao;

import beforeAfter.BeforeAfterPreTesty;
import entity.PrezencnaListina;
import entity.Ucastnik;
import factory.Factory;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySQLUcastnikDaoTest extends BeforeAfterPreTesty{
    
    private final UcastnikDao ucastnikDao = Factory.INSTANCE.getUcastnikDao();
    private final JdbcTemplate jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
    
    /**
     * Test of pridajUcastnika method, of class MySQLUcastnikDao.
     */
    @Test
    public void testPridajUcastnika() {
        System.out.println("pridajUcastnika");
        
        String meno = "Jozef";
        String priezvisko = "Dobrý";
        
        Long idUcastnika = ucastnikDao.pridajUcastnika(meno, priezvisko);
        
        String sql = "SELECT * FROM ucastnik WHERE id = 4";
        
        BeanPropertyRowMapper<Ucastnik> mapper = BeanPropertyRowMapper.newInstance(Ucastnik.class);
        Ucastnik u = jdbcTemplate.queryForObject(sql, mapper);
        
        assertEquals("Jozef", u.getMeno());
        assertEquals("Dobrý", u.getPriezvisko());
        
        assertEquals(new Long(4), idUcastnika);        
    }

    /**
     * Test of vratUcastnikov method, of class MySQLUcastnikDao.
     */
    @Test
    public void testVratUcastnikov() {
        System.out.println("vratUcastnikov");
        
        PrezencnaListina prezencnejListina = new PrezencnaListina();
        prezencnejListina.setId(3L);
        
        List<Ucastnik> ucastnici = ucastnikDao.vratUcastnikov(prezencnejListina);
        assertEquals(2, ucastnici.size());
        
        assertEquals("Kapusta", ucastnici.get(0).getPriezvisko());
        assertEquals("Eva", ucastnici.get(1).getMeno());
    }

    /**
     * Test of dajUcastnika method, of class MySQLUcastnikDao.
     */
    @Test
    public void testDajUcastnika() {
        System.out.println("dajUcastnika");
        
        Long idUcastnika = 3L;
        
        Ucastnik u = ucastnikDao.dajUcastnika(idUcastnika);
        assertEquals(new Long(3L), u.getId());
        assertEquals("Igor", u.getMeno());
        assertEquals("Tichi", u.getPriezvisko());        
    }
    
}
