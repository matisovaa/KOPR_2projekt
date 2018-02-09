
package beforeAfter;

import com.ibatis.common.jdbc.ScriptRunner;
import factory.Factory;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;

public class BeforeAfterPreTesty {
    
    private static final String DB_BEFORE_SCRIPT = "before.sql";     
    private static final String DB_AFTER_SCRIPT = "after.sql";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/KOPR_evidencia_test";    
    private static final String DB_DATABASE = "KOPR_evidencia_test";    

    private static final String DB_USER = "administratorTest";
    private static final String DB_PASSWORD = "admintest";

    protected static Connection connection;
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Class.forName(JDBC_DRIVER);
        
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setDatabaseName(DB_DATABASE);
        dataSource.setUser(DB_USER);        
        dataSource.setPassword(DB_PASSWORD);

        Factory.INSTANCE.setJdbcTemplate(new JdbcTemplate(dataSource));
       
    }
    
    @Before
    public void setUp() throws IOException, SQLException {
        ScriptRunner runner = new ScriptRunner(connection, false, true);
        BufferedReader b = new BufferedReader(new FileReader(DB_BEFORE_SCRIPT));        
        runner.runScript(b);
    }  
    
    @After
    public void tearDownClass() throws SQLException, IOException {
        ScriptRunner runner = new ScriptRunner(connection, false, true);
        BufferedReader b = new BufferedReader(new FileReader(DB_AFTER_SCRIPT));        
        runner.runScript(b);        
    } 
    
}
