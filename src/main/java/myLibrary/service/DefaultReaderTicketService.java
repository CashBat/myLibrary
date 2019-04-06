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

import myLibrary.reposit.LibraryRepository;
import myLibrary.reposit.model.Book;
import myLibrary.reposit.model.Reader;
import myLibrary.reposit.model.ReaderTicket;
import myLibrary.reposit.model.RecordReaderTicket;
import myLibrary.reposit.qualifier.RepReaderTicket;
import myLibrary.reposit.qualifier.RepRecordReaderTicket;
import myLibrary.rest.exception.NoAccessBookException;
import myLibrary.rest.exception.NotFoundReaderTicketException;
import myLibrary.rest.exception.NotFoundRecordsReaderTicketException;
import myLibrary.rest.exception.RiderTicketProcessingException;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.interfasec.ReaderTicketService;
import myLibrary.service.model.RentalInfo;

@Stateless
public class DefaultReaderTicketService implements ReaderTicketService {

	@Inject
	@RepReaderTicket
	LibraryRepository<ReaderTicket> repReaderTicket;

	@Inject
	@RepRecordReaderTicket
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;

	@Inject
	BookService bookService;

	final static int STATUSDEBT = -1;

	public DefaultReaderTicketService() {
	}

	public DefaultReaderTicketService(LibraryRepository<ReaderTicket> repReaderTicket, BookService bookService) {
		this.repReaderTicket = repReaderTicket;
		this.bookService = bookService;
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
	
	private Integer getStatusRental(Date dateIssue, Date returnDate, int quantityRentDay) {
		int result = 1;

		if (returnDate == null) {
			result = DateUtils.truncatedCompareTo(DateUtils.addDays(dateIssue, quantityRentDay), new Date(),
					Calendar.DAY_OF_MONTH);
		}

		return result;
	}

	private RecordReaderTicket createRecord(ReaderTicket readerTicket, RentalInfo rentalInfo, Book book) {
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

	private void updateRecord(RecordReaderTicket recordReaderTicket, RentalInfo rentalInfo) {
		recordReaderTicket.setQuantityRentDay(rentalInfo.getQuantityRentDay());

		if (rentalInfo.getDateIssue() == null || rentalInfo.getDateIssue().isEmpty()) {
			throw new IllegalStateException("Not ready to work. Null or empty rentalInfo: DateIssue");
		}

		recordReaderTicket.setDateIssue(getStringToData(rentalInfo.getDateIssue()));

		if (rentalInfo.getReturnDate() != null) {

			if (!rentalInfo.getReturnDate().isEmpty()) {
				recordReaderTicket.setReturnDate(getStringToData(rentalInfo.getReturnDate()));
			} else {
				recordReaderTicket.setReturnDate(null);
			}

		}

	}
	
	
	@Override
	public Collection<RentalInfo> getRentalForReaderTicked(int idReaderTicked) {

		ReaderTicket readerTicket = repReaderTicket.getEntity(idReaderTicked);

		if (readerTicket == null) {
			throw new NotFoundReaderTicketException();
		}

		if (readerTicket.getRecords().isEmpty()) {
			throw new NotFoundRecordsReaderTicketException();
		}

		RentalInfo rental;
		List<RentalInfo> rentalInfoBooks = new ArrayList<RentalInfo>();

		for (RecordReaderTicket record : readerTicket.getRecords()) {
			rental = new RentalInfo();
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
			System.out.println(repRecordReaderTicket.values().size());
			RecordReaderTicket recordReaderTicket = repRecordReaderTicket.getEntity(idRecord);
			ReaderTicket readerTicket = recordReaderTicket.getReaderTicket();
			readerTicket.removeRecord(recordReaderTicket);
			repReaderTicket.update(readerTicket);
			System.out.println(repRecordReaderTicket.values().size());
			bookService.openAccess(recordReaderTicket.getBook());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RiderTicketProcessingException("Неудалось удалить запись");
		}

	}

	@Override
	public void editRecordReaderTicket(RentalInfo rentalInfo) {
		try {

			if (rentalInfo == null) {
				throw new NullPointerException("null pointer: rentalInfo");
			}

			ReaderTicket readerTicket = repReaderTicket.getEntity(rentalInfo.getIdRiderTicket());
			RecordReaderTicket recordReaderTicket = readerTicket.getRecord(rentalInfo.getIdRecordRiderTicket());
			updateRecord(recordReaderTicket, rentalInfo);

			if (rentalInfo.getReturnDate() != null && !rentalInfo.getReturnDate().isEmpty()) {
				bookService.openAccess(recordReaderTicket.getBook());
			} else {
				bookService.closeAccess(recordReaderTicket.getBook());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RiderTicketProcessingException("Неудалось обновить запись");
		}

	}

	@Override
	public void addRecordReaderTicket(RentalInfo rentalInfo) {
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
			readerTicket.addRecord(createRecord(readerTicket, rentalInfo, book));
			repReaderTicket.update(readerTicket);

		} catch (NoAccessBookException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RiderTicketProcessingException("Неудалось добавить запись");
		}

	}

	
	@Override
	public List<RecordReaderTicket> getDebtBookRecords(ReaderTicket readerTicket) {
		int statusRental;
		List<RecordReaderTicket> debtBookRecords = new ArrayList<RecordReaderTicket>();
		if (readerTicket == null) {
			throw new NotFoundReaderTicketException();
		}
		for (RecordReaderTicket recordRiderTicket : readerTicket.getRecords()) {
			statusRental = getStatusRental(recordRiderTicket.getDateIssue(), recordRiderTicket.getReturnDate(),
					recordRiderTicket.getQuantityRentDay());
			if (statusRental == STATUSDEBT) {
				debtBookRecords.add(recordRiderTicket);
			}
		}
		return debtBookRecords;
	}
	
	@Override
	public Collection<ReaderTicket> getReaderTickets() {
		return repReaderTicket.values();
	}





}
