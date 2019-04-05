package myLibrary.rest.exception;

public class JasperExportReportException extends RuntimeException {
	
	private static final long serialVersionUID = 8627356992324523131L;

	public JasperExportReportException() {
		super("error exportReportToPdfStream");
	}
	
}
