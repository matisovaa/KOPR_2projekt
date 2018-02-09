
package evidencia.dao;

import entity.PrezencnaListina;
import entity.Ucast;
import entity.Ucastnik;
import java.time.LocalDateTime;
import java.util.List;

public interface PrezencnaListinaDao {
    
    public Long pridajPrezencnuListinu(Long idPredmetu, LocalDateTime datumACas);
    
    public void zapisUcast(Ucast ucast);
    
    public List<PrezencnaListina> vratPrezencneListiny(Ucastnik ucastnik);
    
    public PrezencnaListina dajPrezencnuListinu(Long idPrezencnejListiny);
    
}
