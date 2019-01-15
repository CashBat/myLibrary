package myLibrary.reposit;

import javax.enterprise.context.ApplicationScoped;

import myLibrary.entity.RecordReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;

@ApplicationScoped
@RepRecordReaderTicket
public class RecordReaderTicketHashMapRepository extends AbstractHashMapLibraryRepository<RecordReaderTicket> {

	/*
	@Inject
	public RecordReaderTicketHashMapRepository( @RepReaderTicket LibraryRepository<ReaderTicket> repReaderTicket) {
		
		this.repReaderTicket = repReaderTicket;
		try {
			initRecordReaderTicket();
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
	}

	private void initRecordReaderTicket() throws ParseException {

		RecordReaderTicket record = new RecordReaderTicket();
		record.setId(getID());
		record.setBook(repBook.getEntity(1));
		 record.setReaderTicket(repReaderTicket.getEntity(1)); 
		record.setDateIssue(new SimpleDateFormat("dd.MM.yyyy").parse("25.12.2018"));
		record.setQuantityRentDay(12);
		record.setReturnDate(new SimpleDateFormat("dd.MM.yyyy").parse("30.12.2018"));
		add(record);

		record = new RecordReaderTicket();
		record.setId(getID());
		record.setBook(repBook.getEntity(3));
		 record.setReaderTicket(repReaderTicket.getEntity(1)); 
		record.setDateIssue(new SimpleDateFormat("dd.MM.yyyy").parse("20.12.2018"));
		record.setQuantityRentDay(12);
		record.setReturnDate(new SimpleDateFormat("dd.MM.yyyy").parse("24.12.2018"));
		add(record);

		record = new RecordReaderTicket();
		record.setId(getID());
		record.setBook(repBook.getEntity(2));
		record.setReaderTicket(repReaderTicket.getEntity(4)); 
		record.setDateIssue(new SimpleDateFormat("dd.MM.yyyy").parse("20.12.2018"));
		record.setQuantityRentDay(12);
		add(record);

	}*/
	
	


}
