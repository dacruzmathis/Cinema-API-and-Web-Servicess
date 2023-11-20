package rest.todo.resources;


import rest.todo.dao.MovieDao;
import rest.todo.dao.MovieDao;
import rest.todo.manage.ManageCity;
import rest.todo.model.Movie;
import rest.todo.model.Movie;
import rest.todo.dao.MovieDao;
import rest.todo.model.Movie;
import rest.todo.model.ReleaseWindow;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/// Will map the resource to the URL Movies
@Path("/movies")
public class MoviesResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    // Return the list of Movies to the user in the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Movie> getMoviesBrowser() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.addAll(MovieDao.instance.getModel().values());
        return movies;
    }

    // Return the list of Movies for applications
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.addAll(MovieDao.instance.getModel().values());
        return movies;
    }

    // retuns the number of Movies
    // Use http://localhost:8080/com.vogella.jersey.Movie/rest/Movies/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = MovieDao.instance.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newMovie(
            @FormParam("title") String title,
            @FormParam("duration") String duration,
            @FormParam("language") String language,
            @FormParam("director") String director,
            @FormParam("actors") String actors,
            @FormParam("age") String age,
            @Context HttpServletResponse servletResponse) throws IOException {
        Movie movie = new Movie(Movie.incrementId(), title, duration, language, director, actors, age, ReleaseWindow.newReleaseWindow(), ManageCity.getCities());
        /*Movie Movie = new Movie(titre, summary);
        if (description != null) {
            Movie.setDescription(description);
        }*/
        MovieDao.instance.getModel().put(movie.getId(), movie);

        servletResponse.sendRedirect("../create_movie.html");
    }

    // Defines that the next path parameter after Movies is
    // treated as a parameter and passed to the MovieResources
    // Allows to type http://localhost:8080/rest.Movie/rest/Movies/1
    // 1 will be treaded as parameter Movie and passed to MovieResource
    @Path("{movie}")
    public MovieResource getMovie(@PathParam("movie") String id) {
        return new MovieResource(uriInfo, request, id);
    }

}