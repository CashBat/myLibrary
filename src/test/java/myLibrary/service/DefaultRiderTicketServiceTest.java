package myLibrary.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import myLibrary.entity.Book;
import myLibrary.entity.ReaderTicket;
import myLibrary.entity.RecordReaderTicket;
import myLibrary.reposit.ReaderTicketHashMapRepository;
import myLibrary.service.model.RentalInfo;

public class DefaultRiderTicketServiceTest {

	@Test
	public void editRecordReaderTicket_BookReturned() throws ParseException {
		RentalInfo rentalInfo = createTestRentalInfo();
		ReaderTicket actual = createActualRT();
		ReaderTicket expected = createExpectedRT();

		ReaderTicketHashMapRepository rthrMock = Mockito.mock(ReaderTicketHashMapRepository.class);
		Mockito.when(rthrMock.getEntity(rentalInfo.getIdRiderTicket())).thenReturn(actual);
		DefaultRiderTicketService bean = new DefaultRiderTicketService(rthrMock, new DefaultBookService());
		bean.editRecordReaderTicket(rentalInfo);
		
		Assert.assertEquals(actual, expected);
	}

	public ReaderTicket createActualRT() throws ParseException {

		RecordReaderTicket recordReaderTicket = new RecordReaderTicket();

		recordReaderTicket.setId(1);
		recordReaderTicket.setDateIssue(DateUtils.parseDate("2018-12-25", "yyyy-MM-dd"));
		recordReaderTicket.setQuantityRentDay(12);

		Book bookTest = new Book();
		bookTest.setId(1);
		bookTest.setAvailability(false);

		recordReaderTicket.setBook(bookTest);

		ReaderTicket rt = new ReaderTicket();

		rt.setId(1);
		rt.setRecords(new ArrayList<RecordReaderTicket>(Arrays.asList(recordReaderTicket)));

		return rt;

	}
	
	public ReaderTicket createExpectedRT() throws ParseException {

		RecordReaderTicket recordReaderTicket = new RecordReaderTicket();

		recordReaderTicket.setId(1);
		recordReaderTicket.setDateIssue(DateUtils.parseDate("2018-12-25", "yyyy-MM-dd"));
		recordReaderTicket.setReturnDate(DateUtils.parseDate("2018-12-25", "yyyy-MM-dd")); //добавляется дата возврата
		recordReaderTicket.setQuantityRentDay(12);

		Book bookTest = new Book();
		bookTest.setId(1);
		bookTest.setAvailability(true); //появляется доступ к книге

		recordReaderTicket.setBook(bookTest);

		ReaderTicket rt = new ReaderTicket();

		rt.setId(1);
		rt.setRecords(new ArrayList<RecordReaderTicket>(Arrays.asList(recordReaderTicket)));

		return rt;

	}

	public RentalInfo createTestRentalInfo() {
		RentalInfo rentalInfo = new RentalInfo();
		rentalInfo.setDateIssue("2018-12-25");
		rentalInfo.setReturnDate("2018-12-25");
		rentalInfo.setIdRecordRiderTicket(1);
		rentalInfo.setIdRiderTicket(1);
		rentalInfo.setQuantityRentDay(12);
		return rentalInfo;
	}

}
