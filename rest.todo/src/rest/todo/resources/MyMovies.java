package rest.todo.resources;

import rest.todo.dao.MovieDao;
import rest.todo.manage.ManageCity;
import rest.todo.model.Movie;
import rest.todo.model.ReleaseWindow;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/myapi")
public class MyMovies {

    // for the browser
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Movie getMovieHTMLById(@PathParam("id") String id) {
        return MovieDao.instance.getModel().get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post/{title}/{duration}/{language}/{director}/{actors}/{age}")
    public Movie postMovie(
            @PathParam("title") String titre,
            @PathParam("duration") String duration,
            @PathParam("language") String language,
            @PathParam("director") String director,
            @PathParam("actors") String actors,
            @PathParam("age") String age) {
        Movie movie = new Movie(Movie.incrementId(), titre, duration, language, director, actors, age, ReleaseWindow.newReleaseWindow(), ManageCity.getCities());
        MovieDao.instance.getModel().put(movie.getId(), movie);
        return movie;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put/{id}/{title}")
    public Movie putMovie(@PathParam("id") String id, @PathParam("title") String title) {
        return MovieDao.instance.getModel().get(id).setTitle(title);
    }

    @DELETE
    @Path("/delete/{id}")
    public String deleteMovie(@PathParam("id") String id) {
        Movie c = MovieDao.instance.getModel().get(id);
        if(c==null)
            return "Delete: Movie with " + id +  " not found";
        else {
            MovieDao.instance.getModel().remove(id);
            return "Delete successfully";
        }
    }
}