package myLibrary.rest.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotFoundException extends RuntimeException {


	private static final long serialVersionUID = -6239248395436356473L;

	public NotFoundException(String message) {
		super(message);
	}

}
