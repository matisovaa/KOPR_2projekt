
package evidencia.dao;

import entity.Predmet;

public interface PredmetDao {    
    
    public Long pridajPredmet(String nazovPredmetu);
    
    public Predmet dajPredmet(Long idPredmetu);
    
}
