package myLibrary.rest.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotFoundRecordsReaderTicketException extends NotFoundException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1909620178377753539L;

	public NotFoundRecordsReaderTicketException() {
		super("Записи отсутствуют");
	}

	public NotFoundRecordsReaderTicketException(String message) {
		super(message);

	}

}
