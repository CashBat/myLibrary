package myLibrary.service.exception;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NotRecordsException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3829069470330133821L;

	public NotRecordsException() {
	super();
		
	}

	public NotRecordsException(int status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(Response response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(Status status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message, int status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message, Response response) {
		super(message, response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message, Status status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message, Throwable cause, int status) {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message, Throwable cause, Response response) {
		super(message, cause, response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message, Throwable cause, Status status) throws IllegalArgumentException {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(String message) {
	
		super(Response.status(Response.Status.NOT_FOUND).
			    entity(message).type("text/plain").build());
	}

	public NotRecordsException(Throwable cause, int status) {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(Throwable cause, Response response) {
		super(cause, response);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(Throwable cause, Status status) throws IllegalArgumentException {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	

}
