package myLibrary.rest.exception;

public class TypeMismatchException extends RuntimeException {

	private static final long serialVersionUID = -6277534236158336003L;

	public TypeMismatchException(String message) {
		super(message);
	}

}
