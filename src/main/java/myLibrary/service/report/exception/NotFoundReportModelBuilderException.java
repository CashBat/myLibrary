package myLibrary.service.report.exception;

public class NotFoundReportModelBuilderException extends RuntimeException {
	
	private static final long serialVersionUID = -680411728492964911L;

	public NotFoundReportModelBuilderException(int idReport) {
		super("Ошибка при инициализации сборщика. Не нашлось сборщика удовлетворяющего параметру, для: " + idReport);
	}
	
}
