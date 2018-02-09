
package server;

import entity.Predmet;
import entity.PrezencnaListina;
import entity.Ucast;
import entity.Ucastnik;
import evidencia.dao.PredmetDao;
import evidencia.dao.PrezencnaListinaDao;
import evidencia.dao.UcastnikDao;
import exceptions.NeexistujucaPrezencnaListinaException;
import exceptions.NeexistujuciUcastnikException;
import exceptions.NepodariloSaVyrobitPrezencnuListinuException;
import exceptions.SimpleExceptionBean;
import factory.Factory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class EvidenciaService {
    
    private final PredmetDao predmetDao = Factory.INSTANCE.getPredmetDao();
    private final PrezencnaListinaDao prezencnaListinaDao = Factory.INSTANCE.getPrezencnaListinaDao();
    private final UcastnikDao ucastnikDao = Factory.INSTANCE.getUcastnikDao();
    
    public Long pridajPredmet(@WebParam(name = "nazov") String nazovPredmetu){
        return predmetDao.pridajPredmet(nazovPredmetu);
    }
    
    public Long pridajUcastnika(@WebParam(name = "meno") String meno, @WebParam(name = "priezvisko") String priezvisko){
        return ucastnikDao.pridajUcastnika(meno, priezvisko);
    }
    
    //YYYY-MM-DDTHH:MM:SSZ
    /**
     * Prida novu prezencnu listinu len ak sa predmet so zadanym id a vsetci ucastnici so zadanymi id v databaze nachadzaju.
     * @param idPredmetu
     * @param datumACas
     * @param idUcastnikov
     * @return id novej prezencnej listiny
     * @throws exceptions.NepodariloSaVyrobitPrezencnuListinuException
     */
    public Long pridajPrezencnuListinu(@WebParam(name = "idPredmetu") Long idPredmetu, 
            @WebParam(name = "datumACas") Date datumACas, @WebParam(name = "idUcastnikov") List<Long> idUcastnikov) throws NepodariloSaVyrobitPrezencnuListinuException{   
        
        Predmet predmet = predmetDao.dajPredmet(idPredmetu);        
        if(predmet == null){
            throw new NepodariloSaVyrobitPrezencnuListinuException("Predmet so zadanym id neexistuje!", new SimpleExceptionBean());
        }
        
        String sprava = "";
        for (Long idUcastnika : idUcastnikov) {
            Ucastnik ucastnik = ucastnikDao.dajUcastnika(idUcastnika);
            if(ucastnik == null){
                sprava = sprava + idUcastnika + ", ";
            }
        }
        if (!sprava.equals("")){
            throw new NepodariloSaVyrobitPrezencnuListinuException("Ucastnici s tymito id neexistuju: " + sprava, new SimpleExceptionBean());
        }
        
        LocalDateTime ldt;
        try{
            ldt = LocalDateTime.ofInstant(datumACas.toInstant(), ZoneId.systemDefault());
        }catch(NullPointerException e){
            throw new NepodariloSaVyrobitPrezencnuListinuException("Chyba v zadanom datume!", new SimpleExceptionBean());
        }
        
        Long idPrezencnejListiny = prezencnaListinaDao.pridajPrezencnuListinu(idPredmetu, ldt);
        
        for (Long idUcastnika : idUcastnikov) {
            Ucast ucast = new Ucast();
            ucast.setPrezencnaListinaId(idPrezencnejListiny);
            ucast.setUcastnikId(idUcastnika);
            prezencnaListinaDao.zapisUcast(ucast);
        }      
        
        return idPrezencnejListiny;
    }
    
    public List<Ucastnik> vratUcastnikov(@WebParam(name = "idPrezencnejListiny") Long idPrezencnejListiny) throws NeexistujucaPrezencnaListinaException{
        
        PrezencnaListina prezencnaListina = prezencnaListinaDao.dajPrezencnuListinu(idPrezencnejListiny);
        if(prezencnaListina == null){
            throw new NeexistujucaPrezencnaListinaException("Prezencna listina so zadanym id neexistuje!", new SimpleExceptionBean());
        }
        
        List<Ucastnik> ucastnici = ucastnikDao.vratUcastnikov(prezencnaListina);        
        return ucastnici;
    }
    
    public List<PrezencnaListina> vratPrezencneListiny(@WebParam(name = "idUcastnika") Long idUcastnika) throws NeexistujuciUcastnikException {
        
        Ucastnik ucastnik = ucastnikDao.dajUcastnika(idUcastnika);
        if(ucastnik == null){
            throw new NeexistujuciUcastnikException("Ucastnik so zadanym id neexistuje!", new SimpleExceptionBean());
        }
        
        return prezencnaListinaDao.vratPrezencneListiny(ucastnik);
    }
}
