
package factory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import evidencia.dao.MySQLPredmetDao;
import evidencia.dao.MySQLPrezencnaListinaDao;
import evidencia.dao.MySQLUcastnikDao;
import evidencia.dao.PredmetDao;
import evidencia.dao.UcastnikDao;
import evidencia.dao.PrezencnaListinaDao;
import org.springframework.jdbc.core.JdbcTemplate;
import server.EvidenciaService;

public enum Factory {
    
    INSTANCE;
    private JdbcTemplate jdbcTemplate;
    private PredmetDao predmetDao;
    private UcastnikDao ucastnikDao;
    private PrezencnaListinaDao prezencnaListinaDao;
    private EvidenciaService evidenciaService;
    
    public synchronized JdbcTemplate getJdbcTemplate() {
	if (jdbcTemplate == null) {
            MysqlDataSource dataSource;
            dataSource = new MysqlDataSource();
            // pre spustenie service na normalnej netestovacej databaze, 
            // ktorej skript na vytvorenie je v KOPR_2projekt/DB_evidencia_ucasti.sql
            /*
            dataSource.setDatabaseName("KOPR_evidencia");
            dataSource.setUser("administrator");
            dataSource.setPassword("admin1");
            */
            
            // pristup k testovacej databaze
            dataSource.setDatabaseName("KOPR_evidencia_test");
            dataSource.setUser("administratorTest");
            dataSource.setPassword("admintest");
            jdbcTemplate = new JdbcTemplate(dataSource);
	}
	return jdbcTemplate;
    }

    public synchronized void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    public synchronized PredmetDao getPredmetDao() {
	if (predmetDao == null) {
            predmetDao = new MySQLPredmetDao();
	}
	return predmetDao;
    }    
    
    public synchronized UcastnikDao getUcastnikDao() {
	if (ucastnikDao == null) {
            ucastnikDao = new MySQLUcastnikDao();
	}
	return ucastnikDao;
    }
    
    public synchronized PrezencnaListinaDao getPrezencnaListinaDao() {
	if (prezencnaListinaDao == null) {
            prezencnaListinaDao = new MySQLPrezencnaListinaDao();
	}
	return prezencnaListinaDao;
    }
    
    public synchronized EvidenciaService getEvidenciaService() {
	if (evidenciaService == null) {
            evidenciaService = new EvidenciaService();
	}
	return evidenciaService;
    }
}
