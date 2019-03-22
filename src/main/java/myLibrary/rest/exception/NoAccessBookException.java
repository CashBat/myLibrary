package myLibrary.rest.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class NoAccessBookException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoAccessBookException() {
		super("Запись невозможна.Выбранная книга отсутствует в хранилище");
	}

	public NoAccessBookException(String message) {
		super(message);
	}

}
