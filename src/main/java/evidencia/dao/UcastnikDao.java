
package evidencia.dao;

import entity.PrezencnaListina;
import entity.Ucastnik;
import java.util.List;

public interface UcastnikDao {
    
    public Long pridajUcastnika(String meno, String priezvisko);
    
    public List<Ucastnik> vratUcastnikov(PrezencnaListina prezencnaListina);
    
    public Ucastnik dajUcastnika(Long idUcastnika);
    
}
