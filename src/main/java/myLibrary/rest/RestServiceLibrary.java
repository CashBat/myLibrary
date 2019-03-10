package myLibrary.rest;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import myLibrary.entity.RecordReaderTicket;
import myLibrary.rest.exception.NotFoundReaderTicketException;
import myLibrary.rest.exception.NotFoundRecordsReaderTicketException;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.interfasec.RiderTicketService;
import myLibrary.service.model.Rental;


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
	public Response getAllBooks() throws NotFoundRecordsReaderTicketException {

		return Response.ok(serviceGenre.getAllBooks()).build();
	}

	@GET
	@Path(value = "/rentalInfoBooks/{idReaderTicked}")
	public Response getRentalInfoBooksByReaderTickedId(@PathParam(value = "idReaderTicked") Integer idReaderTicked)
			throws NotFoundRecordsReaderTicketException, NotFoundReaderTicketException {
		return Response.ok(serviceRiderTicket.getRentalForReaderTicked(idReaderTicked)).build();
	}
	
	@GET
	@Path(value = "/reader/{idReaderTicked}")
	public Response getReaderByReaderTickedId(@PathParam(value = "idReaderTicked") Integer idReaderTicked)
			throws NotFoundRecordsReaderTicketException, NotFoundReaderTicketException {
		return Response.ok(serviceRiderTicket.getReaderByReaderTickedId(idReaderTicked)).build();
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
	
	
	@POST
	  @Path(value = "rental/add")
	  public Response addRecordReaderTicket(Rental rentalt) {
		serviceRiderTicket.addRecordReaderTicket(rentalt);
	    return Response.ok(rentalt).build();
	  }

}
