package myLibrary.rest.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotAvailableBookException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1249844174449183214L;

	public NotAvailableBookException() {
		super("В данный момент книга не доступна");
	}

	public NotAvailableBookException(String message) {
		super(message);

	}

}
