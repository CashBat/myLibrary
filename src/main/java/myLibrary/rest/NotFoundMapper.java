package myLibrary.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import myLibrary.service.exception.NotFoundException;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
	}

}
