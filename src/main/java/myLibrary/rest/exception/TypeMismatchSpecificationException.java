package myLibrary.rest.exception;

public class TypeMismatchSpecificationException extends TypeMismatchException {

	private static final long serialVersionUID = -4941260485225985313L;

	public TypeMismatchSpecificationException(String specification, String typeSpecification, String typeRepository) {
		super("Ошибка в спецификации запроса - " + specification + ". " + "SpecificationType:" + typeSpecification
				+ ", does not match repositoryType:" + typeRepository + ".");
	}

}
