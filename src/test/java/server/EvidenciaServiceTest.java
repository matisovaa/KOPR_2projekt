
package server;

import beforeAfter.BeforeAfterPreTesty;
import entity.Predmet;
import entity.PrezencnaListina;
import entity.Ucast;
import entity.Ucastnik;
import exceptions.NeexistujucaPrezencnaListinaException;
import exceptions.NeexistujuciUcastnikException;
import exceptions.NepodariloSaVyrobitPrezencnuListinuException;
import factory.Factory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import rowmapper.PrezencnaListinaRowMapper;

public class EvidenciaServiceTest extends BeforeAfterPreTesty{    
    
    private final JdbcTemplate jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
    private final EvidenciaService evidenciaService = Factory.INSTANCE.getEvidenciaService();
    
    /**
     * Test of pridajPredmet method, of class EvidenciaService.
     */
    @Test
    public void testPridajPredmet() {
        System.out.println("pridajPredmet");
        
        String nazovPredmetu = "Databázy";         
        Long idPredmet = evidenciaService.pridajPredmet(nazovPredmetu);        
        
        String sql = "SELECT * FROM predmet WHERE id = 4";
        
        BeanPropertyRowMapper<Predmet> mapper = BeanPropertyRowMapper.newInstance(Predmet.class);
        Predmet p = jdbcTemplate.queryForObject(sql, mapper);
        
        assertEquals("Databázy", p.getNazov()); 
        
        assertEquals(new Long(4), idPredmet); 
    }

    /**
     * Test of pridajUcastnika method, of class EvidenciaService.
     */
    @Test
    public void testPridajUcastnika() {
        System.out.println("pridajUcastnika");
        
        String meno = "Jozef";
        String priezvisko = "Dobrý";
        
        Long idUcastnika = evidenciaService.pridajUcastnika(meno, priezvisko);
                
        String sql = "SELECT * FROM ucastnik WHERE id = 4";
        
        BeanPropertyRowMapper<Ucastnik> mapper = BeanPropertyRowMapper.newInstance(Ucastnik.class);
        Ucastnik u = jdbcTemplate.queryForObject(sql, mapper);
        
        assertEquals("Jozef", u.getMeno());
        assertEquals("Dobrý", u.getPriezvisko());
        
        assertEquals(new Long(4), idUcastnika);
    }

    /**
     * Test of pridajPrezencnuListinu method, of class EvidenciaService.     
     */
    @Test
    public void testPridajPrezencnuListinu() {
        System.out.println("pridajPrezencnuListinu");
        
        Long idPredmetu = 3L;
        LocalDateTime ldt = LocalDateTime.of(2017, 11, 25, 4, 18, 35);
        Date datumACas = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        List<Long> idUcastnikov = Arrays.asList(2L,1L);
        
        Long idPrezencnejListiny = null;
        try {
            idPrezencnejListiny = evidenciaService.pridajPrezencnuListinu(idPredmetu, datumACas, idUcastnikov);
        } catch (NepodariloSaVyrobitPrezencnuListinuException ex) {
            Logger.getLogger(EvidenciaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Test pridania prezencnej listiny vyhodil vynimku!");
        }
        
        String sql = "SELECT prezencna_listina.id, predmet_id, datum, nazov FROM prezencna_listina " +
                        "JOIN predmet ON prezencna_listina.predmet_id = predmet.id  WHERE prezencna_listina.id = 4";        
        PrezencnaListina p = jdbcTemplate.queryForObject(sql, new PrezencnaListinaRowMapper());
        
        String sql2 = "SELECT * FROM ucast WHERE prezencna_listina_id = 4";
        BeanPropertyRowMapper<Ucast> mapper = BeanPropertyRowMapper.newInstance(Ucast.class);
        List<Ucast> ucasti = jdbcTemplate.query(sql2, mapper);       
        
        assertEquals(new Long(4L), idPrezencnejListiny);
        assertEquals(new Long(3L), p.getPredmet().getId());
        assertEquals("Funkcionalne programovanie", p.getPredmet().getNazov()); 
        LocalDateTime ldt2 = LocalDateTime.ofInstant(p.getDatumACas().toInstant(), ZoneId.systemDefault());        
        assertEquals(LocalDateTime.of(2017, 11, 25, 4, 18, 35), ldt2);
        
        assertEquals(2, ucasti.size());        
    }
    
    /**
     * Test of pridajPrezencnuListinu method, of class EvidenciaService.     
     */
    @Test
    public void testPridajPrezencnuListinuNeexistujuciPredmet() {
        System.out.println("pridajPrezencnuListinu");
        
        Long idPredmetu = 42L;
        LocalDateTime ldt = LocalDateTime.of(2017, 11, 25, 4, 18, 35);
        Date datumACas = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        List<Long> idUcastnikov = Arrays.asList(2L,1L);
        
        Long idPrezencnejListiny = null;
        try {
            idPrezencnejListiny = evidenciaService.pridajPrezencnuListinu(idPredmetu, datumACas, idUcastnikov);
            fail("Test pridania prezencnej listiny nevyhodil vynimku!");
        } catch (NepodariloSaVyrobitPrezencnuListinuException ex) {
            Logger.getLogger(EvidenciaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            assertEquals("Predmet so zadanym id neexistuje!", ex.getMessage());
        }
        
        String sql = "SELECT prezencna_listina.id, predmet_id, datum, nazov FROM prezencna_listina " +
                        "JOIN predmet ON prezencna_listina.predmet_id = predmet.id";        
        List<PrezencnaListina> listiny = jdbcTemplate.query(sql, new PrezencnaListinaRowMapper());
        
        String sql2 = "SELECT * FROM ucast";
        BeanPropertyRowMapper<Ucast> mapper = BeanPropertyRowMapper.newInstance(Ucast.class);
        List<Ucast> ucasti = jdbcTemplate.query(sql2, mapper);       
        
        assertEquals(3, listiny.size());        
        
        assertEquals(5, ucasti.size()); 
    }
    
    /**
     * Test of pridajPrezencnuListinu method, of class EvidenciaService.     
     */
    @Test
    public void testPridajPrezencnuListinuNeexistujuciUcastnici() {
        System.out.println("pridajPrezencnuListinu");
        
        Long idPredmetu = 3L;
        LocalDateTime ldt = LocalDateTime.of(2017, 11, 25, 4, 18, 35);
        Date datumACas = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        List<Long> idUcastnikov = Arrays.asList(2L,11L);
        
        Long idPrezencnejListiny = null;
        try {
            idPrezencnejListiny = evidenciaService.pridajPrezencnuListinu(idPredmetu, datumACas, idUcastnikov);
            fail("Test pridania prezencnej listiny nevyhodil vynimku!");
        } catch (NepodariloSaVyrobitPrezencnuListinuException ex) {
            Logger.getLogger(EvidenciaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            assertEquals("Ucastnici s tymito id neexistuju: 11, ", ex.getMessage());
        }
        
        String sql = "SELECT prezencna_listina.id, predmet_id, datum, nazov FROM prezencna_listina " +
                        "JOIN predmet ON prezencna_listina.predmet_id = predmet.id";        
        List<PrezencnaListina> listiny = jdbcTemplate.query(sql, new PrezencnaListinaRowMapper());
        
        String sql2 = "SELECT * FROM ucast";
        BeanPropertyRowMapper<Ucast> mapper = BeanPropertyRowMapper.newInstance(Ucast.class);
        List<Ucast> ucasti = jdbcTemplate.query(sql2, mapper);       
        
        assertEquals(3, listiny.size());        
        
        assertEquals(5, ucasti.size()); 
    }

    /**
     * Test of vratUcastnikov method, of class EvidenciaService.
     */
    @Test
    public void testVratUcastnikov() {
        System.out.println("vratUcastnikov");
        
        Long idPrezencnejListiny = 3L;        
        
        List<Ucastnik> ucastnici = null;
        try {
            ucastnici = evidenciaService.vratUcastnikov(idPrezencnejListiny);
        } catch (NeexistujucaPrezencnaListinaException ex) {
            Logger.getLogger(EvidenciaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Test vrat ucastnikov vyhodil vynimku!");
        }
        
        assertEquals(2, ucastnici.size());
        
        assertEquals("Kapusta", ucastnici.get(0).getPriezvisko());
        assertEquals("Eva", ucastnici.get(1).getMeno());
    }
    
    /**
     * Test of vratUcastnikov method, of class EvidenciaService.
     */
    @Test
    public void testVratUcastnikovNeexistujucaPrezencnaListina() {
        System.out.println("vratUcastnikov");
        
        Long idPrezencnejListiny = 36L;        
        
        List<Ucastnik> ucastnici = null;
        try {
            ucastnici = evidenciaService.vratUcastnikov(idPrezencnejListiny);
            fail("Test vratenia ucastnikov nevyhodil vynimku!");
        } catch (NeexistujucaPrezencnaListinaException ex) {
            Logger.getLogger(EvidenciaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            assertEquals("Prezencna listina so zadanym id neexistuje!", ex.getMessage());
        }
        
        assertEquals(null, ucastnici);       
    }

    /**
     * Test of vratPrezencneListiny method, of class EvidenciaService.
     */
    @Test
    public void testVratPrezencneListiny() {
        System.out.println("vratPrezencneListiny");
        
        Long idUcastnika = 2L;
        
        List<PrezencnaListina> listiny = null;
        try {
            listiny = evidenciaService.vratPrezencneListiny(idUcastnika);
        } catch (NeexistujuciUcastnikException ex) {
            Logger.getLogger(EvidenciaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Test vrat ucastnikov vyhodil vynimku!");
        }
        
        assertEquals(2, listiny.size());        
    }
    
    /**
     * Test of vratPrezencneListiny method, of class EvidenciaService.
     */
    @Test
    public void testVratPrezencneListinyNeexistujuciUcastnik() {
        System.out.println("vratPrezencneListiny");
        
        Long idUcastnika = 42L;
        
        List<PrezencnaListina> listiny = null;
        try {
            listiny = evidenciaService.vratPrezencneListiny(idUcastnika);
            fail("Test vrat ucastnikov nevyhodil vynimku!");
        } catch (NeexistujuciUcastnikException ex) {
            Logger.getLogger(EvidenciaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            assertEquals("Ucastnik so zadanym id neexistuje!", ex.getMessage());
        }
        
        assertEquals(null, listiny);        
    }
    
}
