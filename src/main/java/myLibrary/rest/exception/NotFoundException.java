package myLibrary.rest.exception;

import java.io.IOException;


public class NotFoundException extends IOException {

	private static final long serialVersionUID = 3803842565037672793L;

	public NotFoundException(String message) {
		super(message);
	}

}
