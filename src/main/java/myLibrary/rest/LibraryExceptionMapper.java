package myLibrary.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class LibraryExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException exception) {
		return Response.status(500).entity(exception.getMessage()).type("text/plain").build();
	}	
}
