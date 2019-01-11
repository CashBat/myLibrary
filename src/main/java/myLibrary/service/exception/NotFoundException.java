package myLibrary.service.exception;

import javax.ejb.ApplicationException;
import javax.ws.rs.WebApplicationException;

@ApplicationException
public class NotFoundException  extends WebApplicationException {

	private static final long serialVersionUID = -6034983270973548189L;

	public NotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message) {
		
		super(message);
		
	}

	
	 
}

	  
