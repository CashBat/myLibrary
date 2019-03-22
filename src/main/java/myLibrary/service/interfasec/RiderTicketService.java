package myLibrary.service.interfasec;

import java.util.Collection;

import myLibrary.entity.Reader;
import myLibrary.entity.RecordReaderTicket;
import myLibrary.service.model.Rental;

public interface RiderTicketService {

	Collection<Rental> getRentalForReaderTicked(int idReaderTicked);

	Reader getReaderByReaderTickedId(int idReaderTicked);

	void addRecordReaderTicket(Rental recordReaderTicket);

	void saveRecordReaderTicket(Rental rentalInfo);

	void removeRecordReaderTicket(int idRecord);

}
