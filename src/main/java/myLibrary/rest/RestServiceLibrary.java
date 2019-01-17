package myLibrary.rest;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.itextpdf.text.DocumentException;

import myLibrary.rest.exception.NotReaderTicketException;
import myLibrary.rest.exception.NotRecordsReaderTicketException;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.interfasec.ReportService;
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
	
	@Inject
	ReportService serviceReport;

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
	public Response getRentalInfoBooksByReaderTickedId(@PathParam(value = "idReaderTicked") Integer idReaderTicked)
			throws NotRecordsReaderTicketException, NotReaderTicketException {
		return Response.ok(serviceRiderTicket.getRentalInfoBooksForReaderTicked(idReaderTicked)).build();
	}
	
	@GET
	@Path(value = "/reader/{idReaderTicked}")
	public Response getReaderByReaderTickedId(@PathParam(value = "idReaderTicked") Integer idReaderTicked)
			throws NotRecordsReaderTicketException, NotReaderTicketException {
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
	
	
	@GET
	@Produces("application/zip")
	@Path(value = "/file")
	public Response getZip() {
		

		File filePath = new File("Save");
	    filePath.mkdir();
	    File file = new File(filePath + "\\test.zip");
	    try {
	        file.createNewFile();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	   // final File f = new File("foo.zip");
	    ResponseBuilder response = Response.ok((Object) file);
	    response.header("Content-Disposition", "attachment; filename=" + file.getName());
	    return response.build();
	}
	
	
	@GET
	@Produces("application/pdf")
	@Path(value = "/filePdf")
	public Response getPdf() throws DocumentException, IOException {
	ResponseBuilder response = Response.ok((Object) serviceReport.getPdfReport());
	response.header("Content-Disposition",
			"attachment; filename=new-book.pdf");
	return response.build();}

}
