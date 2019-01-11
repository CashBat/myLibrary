package myLibrary.reposit.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import myLibrary.model.Book;
import myLibrary.model.Reader;
import myLibrary.model.ReaderTicket;
import myLibrary.model.RecordReaderTicket;
import myLibrary.reposit.abs.AbstractHashMapLibraryRepository;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;
import myLibrary.reposit.interfaces.LibraryRepository;

@ApplicationScoped
@RepRecordReaderTicket
public class RecordReaderTicketHashMapRepository extends AbstractHashMapLibraryRepository<RecordReaderTicket> {

	LibraryRepository<Book> repBook;
	LibraryRepository<ReaderTicket> repReaderTicket;

	public RecordReaderTicketHashMapRepository() {
	}

	public RecordReaderTicketHashMapRepository(@RepBook LibraryRepository<Book> repBook,
			@RepReaderTicket LibraryRepository<ReaderTicket> repReaderTicket) {
		this.repBook = repBook;
		this.repReaderTicket = repReaderTicket;
		try {
			initRecordReaderTicket();
		} catch (ParseException e) {
			e.printStackTrace(); // Понять как лучше обработать
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

		record = new RecordReaderTicket();
		record.setId(getID());
		record.setBook(repBook.getEntity(1));
		record.setReaderTicket(repReaderTicket.getEntity(4));
		record.setDateIssue(new SimpleDateFormat("dd.MM.yyyy").parse("20.12.2018"));
		record.setQuantityRentDay(12);
		record.setReturnDate(new SimpleDateFormat("dd.MM.yyyy").parse("24.12.2018"));

		record = new RecordReaderTicket();
		record.setId(getID());
		record.setBook(repBook.getEntity(2));
		record.setReaderTicket(repReaderTicket.getEntity(4));
		record.setDateIssue(new SimpleDateFormat("dd.MM.yyyy").parse("20.12.2018"));
		record.setQuantityRentDay(12);
	}

}
