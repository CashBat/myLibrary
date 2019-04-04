package myLibrary.service.interfasec;

import java.util.Collection;

import myLibrary.reposit.model.Reader;
import myLibrary.service.model.RentalInfo;

public interface RiderTicketService {

	Collection<RentalInfo> getRentalForReaderTicked(int idReaderTicked);

	Reader getReaderByReaderTickedId(int idReaderTicked);

	void addRecordReaderTicket(RentalInfo recordReaderTicket);

	void editRecordReaderTicket(RentalInfo rentalInfo);

	void removeRecordReaderTicket(int idRecord);

}
