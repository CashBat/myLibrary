package myLibrary.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import myLibrary.rest.exception.NotFoundReaderTicketException;
import myLibrary.rest.exception.NotFoundRecordsReaderTicketException;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.interfasec.RiderTicketService;
import myLibrary.service.model.Rental;


//создал ветку
@Path("/main")
public class RestServiceLibrary {
	
	@Context
	 private UriInfo uriInfo;

	@Inject
	BookService serviceGenre;

	@Inject
	RiderTicketService serviceRiderTicket;
	


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/genres")
	public Response getAllGenres() {
		return Response.ok(serviceGenre.getAllGenres()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/books")
	public Response getAllBooks() throws NotFoundRecordsReaderTicketException {

		return Response.ok(serviceGenre.getAllBooks()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/rentalInfoBooks/{idReaderTicked}")
	public Response getRentalInfoBooksByReaderTickedId(@PathParam(value = "idReaderTicked") Integer idReaderTicked)
			throws NotFoundRecordsReaderTicketException, NotFoundReaderTicketException {
		return Response.ok(serviceRiderTicket.getRentalForReaderTicked(idReaderTicked)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/reader/{idReaderTicked}")
	public Response getReaderByReaderTickedId(@PathParam(value = "idReaderTicked") Integer idReaderTicked)
			throws NotFoundRecordsReaderTicketException, NotFoundReaderTicketException {
		return Response.ok(serviceRiderTicket.getReaderByReaderTickedId(idReaderTicked)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/booksAvailable")
	public Response getBooksAvailable() {
		return Response.ok(serviceGenre.getBooksAvailable()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/booksOnHand")
	public Response getBooksOnHand() {
		return Response.ok(serviceGenre.getBooksOnHand()).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	  @Path(value = "rental/add")
	  public Response addRecordReaderTicket(Rental rentalt) {
		serviceRiderTicket.addRecordReaderTicket(rentalt);
		Response a=Response.created(uriInfo.getAbsolutePathBuilder().path(Integer.toString(rentalt.getIdRecordRiderTicket())).build())
				 .build();
				 return a;
	  }
	
	@DELETE
	  @Path(value = "/rental/remove/{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response removeClothes(@PathParam(value = "id") Integer id) {
		serviceRiderTicket.removeRecordReaderTicket(id);
	    return Response.ok().build();
	  }

}
