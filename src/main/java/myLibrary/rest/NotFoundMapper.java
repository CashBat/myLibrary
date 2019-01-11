package myLibrary.rest;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
public class NotFoundMapper implements javax.ws.rs.ext.ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		 return Response.status(404).entity(exception.getMessage()).build();
	}

}
