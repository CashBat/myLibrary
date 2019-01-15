package myLibrary.reposit;

import java.text.ParseException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;

import myLibrary.entity.Book;
import myLibrary.entity.Reader;
import myLibrary.entity.ReaderTicket;
import myLibrary.entity.RecordReaderTicket;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepReader;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;

@ApplicationScoped
@RepReaderTicket
public class ReaderTicketHashMapRepository extends AbstractHashMapLibraryRepository<ReaderTicket> {

	LibraryRepository<Reader> repReader;
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;
	LibraryRepository<Book> repBook;

	public ReaderTicketHashMapRepository() {
	}

	@Inject
	public ReaderTicketHashMapRepository(@RepBook LibraryRepository<Book> repBook,
			@RepReader LibraryRepository<Reader> repReader,
			@RepRecordReaderTicket LibraryRepository<RecordReaderTicket> repRecordReaderTicket) {
		this.repBook = repBook;
		this.repReader = repReader;
		this.repRecordReaderTicket = repRecordReaderTicket;
		initReaderTicket();
	}

	private void initReaderTicket() {

		ReaderTicket readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(1));
		readerTicket.addRecord(createDefaultRecord(repBook.getEntity(1), "25.12.2018", 12, "30.12.2018"));
		readerTicket.addRecord(createDefaultRecord(repBook.getEntity(3), "20.12.2018", 12, "24.12.2018"));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(2));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(3));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(4));

		readerTicket.addRecord(createDefaultRecord(repBook.getEntity(4), "20.12.2018", 12, null));

		add(readerTicket);

	}

	private RecordReaderTicket createDefaultRecord(Book book, String dateIssue, int quantityRentDay,
			String returnDate) {
		String dateFormat = "dd.MM.yyyy";
		RecordReaderTicket record = new RecordReaderTicket();
		try {
			record.setId(repRecordReaderTicket.getID());
			record.setBook(book);
			if (dateIssue != null)
				record.setDateIssue(DateUtils.parseDate(dateIssue, dateFormat));
			record.setQuantityRentDay(quantityRentDay);
			if (returnDate != null)
				record.setReturnDate(DateUtils.parseDate(returnDate, dateFormat));
			repRecordReaderTicket.add(record);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return record;

	}

}
