package myLibrary.service.interfasec;

import java.util.Collection;

import myLibrary.entity.Reader;
import myLibrary.entity.RecordReaderTicket;
import myLibrary.service.model.Rental;

public interface RiderTicketService {

	public Collection<Rental> getRentalForReaderTicked(int idReaderTicked)
			;

	public Reader getReaderByReaderTickedId (int idReaderTicked);

	public void addRecordReaderTicket(Rental recordReaderTicket);

}
