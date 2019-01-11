package myLibrary.service.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotRecordsException2 extends NotFoundException {

	private static final long serialVersionUID = 1707817103831780021L;

	public NotRecordsException2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotRecordsException2(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
