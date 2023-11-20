package rest.todo.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Movie {
    private static String id = "0";
    private String title;
    private String duration;
    private String language;
    private String director;
    private String actors;
    private String age;
    private ReleaseWindow releaseDate;
    private List<City> cities;

    public Movie(){

    }

    public Movie(String id, String title, String duration, String language, String director, String actors, String age, ReleaseWindow releaseWindow, List<City> cities){
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.director = director;
        this.actors = actors;
        this.age = age;
        this.releaseDate = releaseWindow;
        this.cities = cities;
    }

    public static String incrementId(){
        id = new String(String.valueOf(Integer.parseInt(id) + 1));
        return id;
    }

    public String getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return null;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ReleaseWindow getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ReleaseWindow releaseWindow) {
        this.releaseDate = releaseWindow;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}