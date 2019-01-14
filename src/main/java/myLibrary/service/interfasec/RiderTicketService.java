package myLibrary.service.interfasec;

import java.util.Collection;

import myLibrary.rest.exception.NotReaderTicketException;
import myLibrary.rest.exception.NotRecordsReaderTicketException;
import myLibrary.service.model.BookRentalInfo;

public interface RiderTicketService {

	public Collection<BookRentalInfo> getRentalInfoBooksForReaderTicked(int idReaderTicked)
			throws NotRecordsReaderTicketException, NotReaderTicketException;

}
