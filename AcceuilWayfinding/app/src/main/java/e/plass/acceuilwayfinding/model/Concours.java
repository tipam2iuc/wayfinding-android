package e.plass.acceuilwayfinding.model;

import java.util.Date;

public class Concours {
    private String name;
    private Date   limite,debut;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLimite() {
        return limite;
    }

    public void setLimite(Date limite) {
        this.limite = limite;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Concours(String name, Date limite, Date debut) {
        this.name = name;
        this.limite = limite;
        this.debut = debut;
    }
    public String getDate(Date date){
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        return date.getDay()+"/"+date.getMonth()+"/"+date.getYear();
    }
}
