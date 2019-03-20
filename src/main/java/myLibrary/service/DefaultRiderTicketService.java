package myLibrary.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import myLibrary.entity.Book;
import myLibrary.entity.Reader;
import myLibrary.entity.ReaderTicket;
import myLibrary.entity.RecordReaderTicket;
import myLibrary.reposit.LibraryRepository;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;
import myLibrary.rest.exception.NotAvailableBookException;
import myLibrary.rest.exception.NotFoundReaderTicketException;
import myLibrary.rest.exception.NotFoundRecordsReaderTicketException;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.interfasec.RiderTicketService;
import myLibrary.service.model.Rental;

@Stateless
public class DefaultRiderTicketService implements RiderTicketService {

	@Inject
	@RepReaderTicket
	LibraryRepository<ReaderTicket> repReaderTicket;

	@Inject
	@RepRecordReaderTicket
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;

	@Inject
	BookService bookService;

	public Collection<Rental> getRentalForReaderTicked(int idReaderTicked) {

		ReaderTicket readerTicket = repReaderTicket.getEntity(idReaderTicked);

		if (readerTicket == null) {
			throw new NotFoundReaderTicketException();
		}

		if (readerTicket.getRecords().isEmpty()) {
			throw new NotFoundRecordsReaderTicketException();
		}

		Rental rental;
		List<Rental> rentalInfoBooks = new ArrayList<Rental>();

		for (RecordReaderTicket record : readerTicket.getRecords()) {
			rental = new Rental();
			rental.setIdRecordRiderTicket(record.getId());

			if (record.getBook() != null) {
				rental.setIdBook(record.getBook().getId());
			}

			rental.setQuantityRentDay(record.getQuantityRentDay());

			if (record.getDateIssue() != null) {
				rental.setDateIssue(getDataToString(record.getDateIssue()));
			}

			if (record.getReturnDate() != null) {
				rental.setReturnDate(getDataToString(record.getReturnDate()));
			}
			rental.setStatusRental(
					getStatusRental(record.getDateIssue(), record.getReturnDate(), record.getQuantityRentDay()));
			rentalInfoBooks.add(rental);
		}

		return rentalInfoBooks;
	}

	public Reader getReaderByReaderTickedId(int idReaderTicked) {
		ReaderTicket readerTicket = repReaderTicket.getEntity(idReaderTicked);

		if (readerTicket == null) {
			throw new NotFoundReaderTicketException();
		}

		return readerTicket.getReader();

	}

	private Integer getStatusRental(Date dateIssue, Date returnDate, int quantityRentDay) {
		int result = 1;

		if (returnDate == null) {
			result = DateUtils.truncatedCompareTo(DateUtils.addDays(dateIssue, quantityRentDay), new Date(),
					Calendar.DAY_OF_MONTH);
		}

		return result;
	}

	@Override
	public void addRecordReaderTicket(Rental rentalInfo) {

		if (rentalInfo == null) {
			throw new NullPointerException("null pointer: rental");
		}
		
		ReaderTicket readerTicket = repReaderTicket.getEntity(rentalInfo.getIdRiderTicket());
		rentalInfo.setIdRecordRiderTicket(readerTicket.getRecords().size()+1);
		readerTicket.addRecord(createRecord(rentalInfo));	
		bookService.closeAccess(rentalInfo.getIdBook());
		
	}

	private RecordReaderTicket createRecord(Rental rentalInfo) {
		RecordReaderTicket record = new RecordReaderTicket();
		record.setId(rentalInfo.getIdRecordRiderTicket());
	    record.setBook(bookService.getBook(rentalInfo.getIdBook()));
		record.setQuantityRentDay(rentalInfo.getQuantityRentDay());
		record.setDateIssue(getStringToData(rentalInfo.getDateIssue()));
		return record;
	}

	public String getDataToString(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	public Date getStringToData(String dataStr) {
				Date result = null;
				try {
				result = DateUtils.parseDate(dataStr, "yyyy-MM-dd");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return result;
			}
	
	
}
