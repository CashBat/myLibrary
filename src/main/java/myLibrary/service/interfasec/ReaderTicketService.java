package myLibrary.service.interfasec;

import java.util.Collection;
import java.util.List;


import myLibrary.reposit.model.Reader;
import myLibrary.reposit.model.ReaderTicket;
import myLibrary.reposit.model.RecordReaderTicket;
import myLibrary.service.model.RentalInfo;

public interface ReaderTicketService {

	Collection<RentalInfo> getRentalForReaderTicked(int idReaderTicked);

	Reader getReaderByReaderTickedId(int idReaderTicked);

	void addRecordReaderTicket(RentalInfo recordReaderTicket);

	void editRecordReaderTicket(RentalInfo rentalInfo);

	void removeRecordReaderTicket(int idRecord);

	List<RecordReaderTicket> getDebtBookRecords(ReaderTicket readerTicket);
	
	Collection<ReaderTicket> getReaderTickets();
	
	Collection<RecordReaderTicket> getRecordReaderTickets();

}
