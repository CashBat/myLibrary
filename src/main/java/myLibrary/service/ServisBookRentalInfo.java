package myLibrary.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;

import myLibrary.model.Book;
import myLibrary.model.ReaderTicket;
import myLibrary.model.RecordReaderTicket;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;
import myLibrary.reposit.interfaces.LibraryRepository;
import myLibrary.service.model.BookRentalInfo;

@Stateless
public class ServisBookRentalInfo {

	LibraryRepository<Book> repBook;
	LibraryRepository<ReaderTicket> repReaderTicket;
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;

	public ServisBookRentalInfo(@RepBook LibraryRepository<Book> repBook,
			@RepReaderTicket LibraryRepository<ReaderTicket> repReaderTicket,
			@RepRecordReaderTicket LibraryRepository<RecordReaderTicket> repRecordReaderTicket) {
		super();
		this.repBook = repBook;
		this.repReaderTicket = repReaderTicket;
		this.repRecordReaderTicket = repRecordReaderTicket;
	}

	

	public ServisBookRentalInfo() {
	}

	public Collection<BookRentalInfo> getRentalInfoBooksForReaderTicked(int idReaderTicked) {
		BookRentalInfo bookRentalInf;

		List<BookRentalInfo> rentalInfoBooks = new ArrayList<BookRentalInfo>();
		
		ReaderTicket readerTicket = repReaderTicket.getEntity(idReaderTicked);
		
		for(RecordReaderTicket record:readerTicket.getRecords()) {
			bookRentalInf = new BookRentalInfo();
			bookRentalInf.setStatusRental(DateUtils.truncatedCompareTo(new Date(),DateUtils.addDays(record.getDateIssue(),record.getQuantityRentDay()),Calendar.DAY_OF_MONTH));
			rentalInfoBooks.add(bookRentalInf);
		}

		return null;
	}

}
