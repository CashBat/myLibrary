package myLibrary.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ExampleExceptionWebapp extends WebApplicationException {

	private static final long serialVersionUID = -3829069470330133821L;

	public ExampleExceptionWebapp() {
		super();

	}

	public ExampleExceptionWebapp(int status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(Response response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(Status status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message, int status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message, Response response) {
		super(message, response);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message, Status status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message, Throwable cause, int status) {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message, Throwable cause, Response response) {
		super(message, cause, response);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message, Throwable cause, Status status) throws IllegalArgumentException {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(String message) {

		super(Response.status(Response.Status.NOT_FOUND).entity(message).type("text/plain").build());
	}

	public ExampleExceptionWebapp(Throwable cause, int status) {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(Throwable cause, Response response) {
		super(cause, response);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(Throwable cause, Status status) throws IllegalArgumentException {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public ExampleExceptionWebapp(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
