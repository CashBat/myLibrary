package myLibrary.service.exception;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NotRecordsExceptionWebapp extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3829069470330133821L;

	public NotRecordsExceptionWebapp() {
	super();
		
	}

	public NotRecordsExceptionWebapp(int status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(Response response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(Status status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message, int status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message, Response response) {
		super(message, response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message, Status status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message, Throwable cause, int status) {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message, Throwable cause, Response response) {
		super(message, cause, response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message, Throwable cause, Status status) throws IllegalArgumentException {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(String message) {
	
		super(Response.status(Response.Status.NOT_FOUND).
			    entity(message).type("text/plain").build());
	}

	public NotRecordsExceptionWebapp(Throwable cause, int status) {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(Throwable cause, Response response) {
		super(cause, response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(Throwable cause, Status status) throws IllegalArgumentException {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsExceptionWebapp(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	

}
