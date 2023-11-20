package rest.todo.client;

import org.glassfish.jersey.client.ClientConfig;
import rest.todo.manage.ManageCity;
import rest.todo.model.Movie;
import rest.todo.model.ReleaseWindow;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

public class TesterMovie {
    public static void main(String[] args) {

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget service = client.target(getBaseURI());

        System.out.println(service.path("rest").path("movies"));
        // create one Movie
        Movie movie = new Movie(Movie.incrementId(), "la ligne verte", "140", "en", "spielberg", "tom hanks", "14", ReleaseWindow.newReleaseWindow(), ManageCity.getCities());
        Response response = service.path("rest").path("movies").path(movie.getId()).request(MediaType.APPLICATION_JSON).put(Entity.entity(movie,MediaType.APPLICATION_JSON),Response.class);

        // Return code should be 201 == created resource
        System.out.println(response.getStatus());

        // Get the Movies
        System.out.println(service.path("rest").path("movies").request().accept(MediaType.APPLICATION_JSON).get(String.class));

        //If we want a collection of Movies

        //List<Movie> listeMovies=client.target("http://localhost:8080/rest.todo/rest/movies").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Movie>>(){});
/*
        for(rest.todo.model.Movie movieitem:listeMovies){
            System.out.println("Movie id: "+movieitem.getId()+", Movie id : "+movieitem.getId());
        }*/

        // Get JSON for application (Make sure to add the jersey-media-json-jackson dependency to add support for JSON)
        System.out.println(service.path("rest").path("movies").request().accept(MediaType.APPLICATION_JSON).get(String.class));

        // Get XML for application
        System.out.println(service.path("rest").path("movies").request().accept(MediaType.APPLICATION_JSON).get(String.class));

        //Get Movie with id 1
        // Response checkget = service.path("rest").path("Movies/1").request().accept(MediaType.APPLICATION_XML).get();
        Response checkget = service.path("rest").path("movies").path("{movie}").resolveTemplate("movie", "1").request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println(checkget);

        //Delete Movie with id 1
        service.path("rest").path("movies/1").request().delete();

        //Get get all Movies id 1 should be deleted
        System.out.println(service.path("rest").path("movies").request().accept(MediaType.APPLICATION_JSON).get(String.class));

        //Create a Movie
        Movie m3 = new Movie(Movie.incrementId(), "la ligne verte", "140", "en", "spielberg", "tom hanks", "14", ReleaseWindow.newReleaseWindow(), ManageCity.getCities());

        Form form =new Form();
        form.param("id", m3.getId());
        form.param("title", m3.getTitle());
        form.param("duration", m3.getDuration().toString());
        form.param("language", m3.getLanguage());
        form.param("director", m3.getDirector());
        form.param("actors", m3.getActors());
        form.param("age", m3.getAge().toString());
        form.param("cities", m3.getCities().toString());
        form.param("cities", m3.getCities().toString());

        //On peut utiliser Entity.entity() ou bien Entity.form
        // response = service.path("rest").path("Movies").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class);
        response = service.path("rest").path("movies").request().post(Entity.form(form),Response.class);

        System.out.println("Form response " + response.getStatus());

        //Get all the Movies, id 4 should have been created
        System.out.println(service.path("rest").path("movies").request().accept(MediaType.APPLICATION_JSON).get(String.class));

    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/rest.todo").build();
    }
}
