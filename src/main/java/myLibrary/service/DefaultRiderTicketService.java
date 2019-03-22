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
import myLibrary.rest.exception.NoAccessBookException;
import myLibrary.rest.exception.NotFoundReaderTicketException;
import myLibrary.rest.exception.NotFoundRecordsReaderTicketException;
import myLibrary.rest.exception.RiderTicketProcessingException;
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

	@Override
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

	@Override
	public Reader getReaderByReaderTickedId(int idReaderTicked) {
		ReaderTicket readerTicket = repReaderTicket.getEntity(idReaderTicked);

		if (readerTicket == null) {
			throw new NotFoundReaderTicketException();
		}

		return readerTicket.getReader();

	}

	@Override
	public void removeRecordReaderTicket(int idRecord) {
		try {
			RecordReaderTicket recordReaderTicket = repRecordReaderTicket.getEntity(idRecord);		
			recordReaderTicket.getReaderTicket().removeRecord(recordReaderTicket);
			bookService.openAccess(recordReaderTicket.getBook());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RiderTicketProcessingException("Неудалось удалить запись");
		}

	}

	@Override
	public void saveRecordReaderTicket(Rental rentalInfo) {
		try {

			if (rentalInfo == null) {
				throw new NullPointerException("null pointer: rentalInfo");
			}

			ReaderTicket readerTicket = repReaderTicket.getEntity(rentalInfo.getIdRiderTicket());
			RecordReaderTicket recordReaderTicket = readerTicket.getRecord(rentalInfo.getIdRecordRiderTicket());
			updateRecord(recordReaderTicket, rentalInfo);


		} catch (Exception e) {
			e.printStackTrace();
			throw new RiderTicketProcessingException("Неудалось обновить запись");
		}

	}

	@Override
	public void addRecordReaderTicket(Rental rentalInfo) {
		try {

			if (rentalInfo == null) {
				throw new NullPointerException("null pointer: rentalInfo");
			}

			Book book = bookService.getBook(rentalInfo.getIdBook());

			if (book == null) {
				throw new NullPointerException("null pointer: book");
			}

			if (!book.isAvailability()) {
				throw new NoAccessBookException();
			}

			bookService.closeAccess(book);
			ReaderTicket readerTicket = repReaderTicket.getEntity(rentalInfo.getIdRiderTicket());
			repRecordReaderTicket.add(createRecord(readerTicket, rentalInfo, book));
		} catch (NoAccessBookException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RiderTicketProcessingException("Неудалось добавить запись");
		}

	}
	
	private Integer getStatusRental(Date dateIssue, Date returnDate, int quantityRentDay) {
		int result = 1;

		if (returnDate == null) {
			result = DateUtils.truncatedCompareTo(DateUtils.addDays(dateIssue, quantityRentDay), new Date(),
					Calendar.DAY_OF_MONTH);
		}

		return result;
	}

	private RecordReaderTicket createRecord(ReaderTicket readerTicket, Rental rentalInfo, Book book) {
		RecordReaderTicket record = new RecordReaderTicket();
		record.setReaderTicket(readerTicket);
		record.setBook(book);
		record.setQuantityRentDay(rentalInfo.getQuantityRentDay());

		if (rentalInfo.getDateIssue() == null || rentalInfo.getDateIssue().isEmpty()) {
			throw new IllegalStateException("Not ready to work. Null or empty rentalInfo: DateIssue");
		}

		record.setDateIssue(getStringToData(rentalInfo.getDateIssue()));
		return record;
	}

	private void updateRecord(RecordReaderTicket recordReaderTicket, Rental rentalInfo) {
		recordReaderTicket.setQuantityRentDay(rentalInfo.getQuantityRentDay());

		if (rentalInfo.getDateIssue() == null || rentalInfo.getDateIssue().isEmpty()) {
			throw new IllegalStateException("Not ready to work. Null or empty rentalInfo: DateIssue");
		}

		recordReaderTicket.setDateIssue(getStringToData(rentalInfo.getDateIssue()));

		if (rentalInfo.getReturnDate() == null || rentalInfo.getReturnDate().isEmpty()) {
			throw new IllegalStateException("Not ready to work. Null or empty rentalInfo: ReturnDate");
		}

		recordReaderTicket.setReturnDate(getStringToData(rentalInfo.getReturnDate()));
	}

	private String getDataToString(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	private Date getStringToData(String dataStr) {
		Date result = null;
		try {
			result = DateUtils.parseDate(dataStr, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

}
