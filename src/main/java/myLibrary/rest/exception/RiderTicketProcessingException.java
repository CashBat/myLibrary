package myLibrary.rest.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class RiderTicketProcessingException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RiderTicketProcessingException() {
		super("Читательский дневник. Серверная ошибка");
	}

	public RiderTicketProcessingException(String message) {
		super(message+". Серверная ошибка");
	}

}
