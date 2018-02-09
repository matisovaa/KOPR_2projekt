
package rowmapper;

import entity.Predmet;
import entity.PrezencnaListina;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

public class PrezencnaListinaRowMapper implements RowMapper<PrezencnaListina>{

    @Override
    public PrezencnaListina mapRow(ResultSet rs, int i) throws SQLException {
        
        PrezencnaListina p = new PrezencnaListina();
        p.setId(rs.getLong("id"));
        
        Predmet predmet = new Predmet();
        predmet.setId(rs.getLong("predmet_id"));
        predmet.setNazov(rs.getString("nazov"));
        p.setPredmet(predmet);        
        
        Timestamp datum = rs.getTimestamp("datum");
        LocalDateTime ldt = datum.toLocalDateTime();
        Date datumAcasDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        p.setDatumACas(datumAcasDate);
                        
        return p;
    }
    
}
