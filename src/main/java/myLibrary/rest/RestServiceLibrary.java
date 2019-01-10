package myLibrary.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import myLibrary.service.GenreService;
//создал ветку
@Path("/main")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestServiceLibrary {

	@Inject
	GenreService serviceGenre;

	@GET
	@Path(value = "/genres")
	public Response getAllGenres() {
		return Response.ok(serviceGenre.getAllGenres()).build();
	}

	@GET
	@Path(value = "/books")
	public Response getAllBooks() {
		return Response.ok(serviceGenre.getAllBooks()).build();
	}
	
	
}
