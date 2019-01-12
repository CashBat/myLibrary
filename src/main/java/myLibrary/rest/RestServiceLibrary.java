package myLibrary.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import myLibrary.service.GenreService;
import myLibrary.service.ServisBookRentalInfo;
import myLibrary.service.exception.NotRecordsReaderTicketException;

//создал ветку
@Path("/main")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestServiceLibrary {

	@Inject
	GenreService serviceGenre;

	@Inject
	ServisBookRentalInfo servisBookRentalInfo;

	@GET
	@Path(value = "/genres")
	public Response getAllGenres() {
		return Response.ok(serviceGenre.getAllGenres()).build();
	}

	@GET
	@Path(value = "/books")
	public Response getAllBooks() throws NotRecordsReaderTicketException {
		if (true) {
			throw new	NotRecordsReaderTicketException("ServisBookRentalInfo222 - \r\n" + 
					"no records");
			/*throw new NoRecordsException("ServisBookRentalInfo - \r\n" + 
					"no records");*/
	}
		return Response.ok(serviceGenre.getAllBooks()).build();
	}


	@GET
	@Path(value = "/rentalInfoBooks/{idReaderTicked}")
	public Response getAllBooks(@PathParam(value = "idReaderTicked") Integer idReaderTicked) throws NotRecordsReaderTicketException {
		return Response.ok(servisBookRentalInfo.getRentalInfoBooksForReaderTicked(idReaderTicked)).build();
	}


	
	@GET
	@Path(value = "/availabilityBooks")
	public Response getAvailabilityBooks() {
		return Response.ok(serviceGenre.getAvailabilityBooks()).build();
	}

}
