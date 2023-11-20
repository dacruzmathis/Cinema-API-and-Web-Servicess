package rest.todo.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

import static rest.todo.utils.Utils.getUnbookingDate;
import static rest.todo.utils.Utils.setDateFormat;

@XmlRootElement
public class ReleaseWindow {
    private String dateDebut;
    private String dateFin;

    public ReleaseWindow(){

    }

    public ReleaseWindow(String dateDebut, String dateFin){
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public static ReleaseWindow newReleaseWindow(){
        Date releaseDate = new Date();
        return new ReleaseWindow(setDateFormat(releaseDate), setDateFormat(getUnbookingDate(releaseDate)));
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
}