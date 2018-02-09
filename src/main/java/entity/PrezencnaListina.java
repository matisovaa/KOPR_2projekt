
package entity;

import java.util.Date;

public class PrezencnaListina {
    
    private Long id;
    private Predmet predmet;
    private Date datumACas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Date getDatumACas() {
        return datumACas;
    }

    public void setDatumACas(Date datumACas) {
        this.datumACas = datumACas;
    }    
}
