package myLibrary.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;


import myLibrary.reposit.LibraryRepository;
import myLibrary.reposit.model.JasperPrintReport;
import myLibrary.reposit.qualifier.RepReport;
import myLibrary.rest.exception.JasperExportReportException;
import myLibrary.rest.exception.NotFoundReaderTicketException;
import myLibrary.rest.exception.NotFoundRecordsReaderTicketException;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.interfasec.ReportService;
import myLibrary.service.interfasec.ReaderTicketService;
import myLibrary.service.model.RentalInfo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;



//создал ветку
@Path("/main")
public class RestServiceLibrary {
	
	@Context
	 private UriInfo uriInfo;

	@Inject
	BookService serviceGenre;

	@Inject
	ReaderTicketService serviceRiderTicket;
	
	@Inject
	@RepReport
	LibraryRepository<JasperPrintReport> reportRep;
	
	@Inject
	ReportService reportServise;
	
/*	@Inject
	@ReportVersionRep
	InformationRepository<ReportVersion> reportVersionRep;
	*/


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
	  @Path(value = "/rental/add")
	  public Response addRecordReaderTicket(RentalInfo rentalt) {
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
	
	  @PUT
	  @Path(value = "/rental/edit")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response editRecordReaderTicket(RentalInfo rentalInfo) {
		  serviceRiderTicket.editRecordReaderTicket(rentalInfo);
	    return Response.ok().build();
	  }
	
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)//возвращает объект в формате json, без этого выдаст ошибку :не удалось найти тело для объекта хешмеп (Could not find MessageBodyWriter for response object of type: java.util.HashMap$Values of media type: application/signed-exchange;v=b3)
		@Path(value = "reports")
		public Response getReports() {
			return Response.ok(reportRep.values()).build();
		}
	  
	  
	  @GET
		@Produces("application/pdf")
		@Path(value = "/filePdf")
		public Response getPdf(@QueryParam("idReport") int idReport)  {  
		 JasperPrintReport jasperPrintReport= reportServise.getJasperPrintReport(idReport);
		 
			ResponseBuilder response = Response.ok((StreamingOutput) (output) -> {
						try {
							JasperExportManager.exportReportToPdfStream(
									jasperPrintReport.getJasperPrint(), output);
						} catch (JRException e) {
							throw new JasperExportReportException();
						}	
			});
			response.header("Content-Disposition", "attachment; filename="+jasperPrintReport.getReportName()+".pdf");
			return response.build();
		}
	  

}
