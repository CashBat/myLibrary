package myLibrary.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.time.DateUtils;

import myLibrary.entity.Book;
import myLibrary.entity.ReaderTicket;
import myLibrary.entity.RecordReaderTicket;
import myLibrary.reposit.LibraryRepository;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;
import myLibrary.rest.exception.NotRecordsReaderTicketException;
import myLibrary.service.interfasec.RiderTicketService;
import myLibrary.service.model.BookRentalInfo;

@Stateless
public class DefaultRiderTicketService implements RiderTicketService {

	@Inject
	@RepBook
	LibraryRepository<Book> repBook;
	@Inject
	@RepReaderTicket
	LibraryRepository<ReaderTicket> repReaderTicket;
	@Inject
	@RepRecordReaderTicket
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;

	public Collection<BookRentalInfo> getRentalInfoBooksForReaderTicked(int idReaderTicked)
			throws NotRecordsReaderTicketException {

		ReaderTicket readerTicket = repReaderTicket.getEntity(idReaderTicked);

		if (readerTicket.getRecords().isEmpty()) {
			throw new NotRecordsReaderTicketException();
		}

		BookRentalInfo bookRentalInfo;
		List<BookRentalInfo> rentalInfoBooks = new ArrayList<BookRentalInfo>();

		for (RecordReaderTicket record : readerTicket.getRecords()) {
			bookRentalInfo = new BookRentalInfo();
			bookRentalInfo.setRecord(record);
			bookRentalInfo.setStatusRental(
					getStatusRental(record.getDateIssue(), record.getReturnDate(), record.getQuantityRentDay()));
			rentalInfoBooks.add(bookRentalInfo);
		}

		return rentalInfoBooks;
	}

	private Integer getStatusRental(Date dateIssue, Date returnDate, int quantityRentDay) {
		int result = 1;

		if (returnDate == null) {
			result = DateUtils.truncatedCompareTo(DateUtils.addDays(dateIssue, quantityRentDay), new Date(),
					Calendar.DAY_OF_MONTH);
		}
		return result;
	}

}
