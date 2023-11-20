package rest.todo.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class City {
    private String address;
    private List<String> days;
    private List<String> schedules;

    public City(){

    }

    public City(String address, List<String> days, List<String> schedules){
        this.address = address;
        this.days = days;
        this.schedules = schedules;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }
}