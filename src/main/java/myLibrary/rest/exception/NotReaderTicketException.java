package myLibrary.rest.exception;

public class NotReaderTicketException extends NotFoundException {

	private static final long serialVersionUID = 3215414970238322287L;

	public NotReaderTicketException() {
		super("Выбран не существующий дневник");
	}

	public NotReaderTicketException(String message) {
		super(message);

	}

}
