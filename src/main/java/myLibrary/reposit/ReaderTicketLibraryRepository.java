package myLibrary.reposit;

import java.text.ParseException;
import java.util.Iterator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;

import myLibrary.reposit.model.Book;
import myLibrary.reposit.model.Reader;
import myLibrary.reposit.model.ReaderTicket;
import myLibrary.reposit.model.RecordReaderTicket;
import myLibrary.reposit.qualifier.RepBook;
import myLibrary.reposit.qualifier.RepReader;
import myLibrary.reposit.qualifier.RepReaderTicket;
import myLibrary.reposit.qualifier.RepRecordReaderTicket;

@ApplicationScoped
@RepReaderTicket
public class ReaderTicketLibraryRepository extends AbstractLibraryRepository<ReaderTicket> {

	LibraryRepository<Reader> repReader;
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;
	LibraryRepository<Book> repBook;

	public ReaderTicketLibraryRepository() {
	}

	@Inject
	public ReaderTicketLibraryRepository(@RepBook LibraryRepository<Book> repBook,
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
		readerTicket.addRecord(createDefaultRecord(repBook.getEntity(1), "25.12.2019", 12, "30.12.2019"));
		readerTicket.addRecord(createDefaultRecord(repBook.getEntity(3), "20.12.2019", 12, "24.12.2019"));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(2));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(3));
		readerTicket.addRecord(createDefaultRecord(repBook.getEntity(1), "06.12.2019", 12, "10.12.2019"));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(4));

		readerTicket.addRecord(createDefaultRecord(repBook.getEntity(2), "20.12.2019", 12, null));

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

	@Override
	public void update(ReaderTicket readerTicket) {

		Iterator<RecordReaderTicket> it = readerTicket.getRecords().iterator();
		while (it.hasNext()) {
			RecordReaderTicket record = it.next();

			if (record.getId() == null) {
				record.setId(repRecordReaderTicket.getID());
				repRecordReaderTicket.add(record);
			}

    			if (record.getReaderTicket() == null) {
				repRecordReaderTicket.delite(record.getId());
				it.remove();
			}

		}

		super.update(readerTicket);
	}

}
