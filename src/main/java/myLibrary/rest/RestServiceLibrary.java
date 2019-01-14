package myLibrary.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import myLibrary.rest.exception.NotRecordsReaderTicketException;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.interfasec.RiderTicketService;

//создал ветку
@Path("/main")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestServiceLibrary {

	@Inject
	BookService serviceGenre;

	@Inject
	RiderTicketService serviceRiderTicket;

	@GET
	@Path(value = "/genres")
	public Response getAllGenres() {
		return Response.ok(serviceGenre.getAllGenres()).build();
	}

	@GET
	@Path(value = "/books")
	public Response getAllBooks() throws NotRecordsReaderTicketException {

		return Response.ok(serviceGenre.getAllBooks()).build();
	}

	@GET
	@Path(value = "/rentalInfoBooks/{idReaderTicked}")
	public Response getRentalInfoBooksForReaderTicked(@PathParam(value = "idReaderTicked") Integer idReaderTicked)
			throws NotRecordsReaderTicketException {
		return Response.ok(serviceRiderTicket.getRentalInfoBooksForReaderTicked(idReaderTicked)).build();
	}

	@GET
	@Path(value = "/booksAvailable")
	public Response getBooksAvailable() {
		return Response.ok(serviceGenre.getBooksAvailable()).build();
	}

	@GET
	@Path(value = "/booksOnHand")
	public Response getBooksOnHand() {
		return Response.ok(serviceGenre.getBooksOnHand()).build();
	}

}
