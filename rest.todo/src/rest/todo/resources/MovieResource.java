package rest.todo.resources;

import rest.todo.dao.MovieDao;
import rest.todo.model.Movie;
import rest.todo.dao.MovieDao;
import rest.todo.model.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;


public class MovieResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;
    public MovieResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Movie getMovie() {
        Movie Movie = MovieDao.instance.getModel().get(id);
        if(Movie==null)
            throw new RuntimeException("Get: Movie with " + id +  " not found");
        return Movie;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public Movie getMovieHTML() {
        Movie c = MovieDao.instance.getModel().get(id);
        if(c==null)
            throw new RuntimeException("Get: Movie with " + id +  " not found");
        return c;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putMovie(JAXBElement<Movie> Movie) {
        Movie c = Movie.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteMovie() {
        Movie c = MovieDao.instance.getModel().remove(id);
        if(c==null)
            throw new RuntimeException("Delete: Movie with " + id +  " not found");
    }

    private Response putAndGetResponse(Movie Movie) {
        Response res;
        if(MovieDao.instance.getModel().containsKey(Movie.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        MovieDao.instance.getModel().put(Movie.getId(), Movie);
        return res;
    }
}