package myLibrary.service.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotRecordsReaderTicketException extends NotFoundException {

	private static final long serialVersionUID = 3215414970238322287L;

	public NotRecordsReaderTicketException() {
		super("Записи отсутствуют");
	}

	public NotRecordsReaderTicketException(String message) {
		super(message);

	}

}
