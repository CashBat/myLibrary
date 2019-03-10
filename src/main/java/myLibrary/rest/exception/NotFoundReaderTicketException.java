package myLibrary.rest.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotFoundReaderTicketException extends NotFoundException {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -602221647347122805L;

	public NotFoundReaderTicketException() {
		super("Выбран не существующий дневник");
	}

	public NotFoundReaderTicketException(String message) {
		super(message);

	}

}
