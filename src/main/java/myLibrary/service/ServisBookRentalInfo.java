package myLibrary.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.time.DateUtils;

import myLibrary.model.Book;
import myLibrary.model.ReaderTicket;
import myLibrary.model.RecordReaderTicket;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;
import myLibrary.reposit.interfaces.LibraryRepository;
import myLibrary.service.exception.NotRecordsException;
import myLibrary.service.model.BookRentalInfo;

@Stateless
public class ServisBookRentalInfo {

	@Inject
	@RepBook
	LibraryRepository<Book> repBook;
	@Inject
	@RepReaderTicket
	LibraryRepository<ReaderTicket> repReaderTicket;
	@Inject
	@RepRecordReaderTicket
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;

	public Collection<BookRentalInfo> getRentalInfoBooksForReaderTicked(int idReaderTicked) {
		BookRentalInfo bookRentalInf;
		ReaderTicket readerTicket;
		List<BookRentalInfo> rentalInfoBooks = new ArrayList<BookRentalInfo>();
		
		
//		ReaderTicket readerTicket= repReaderTicket.getEntity(idReaderTicked);
		
		try {
		if (repReaderTicket.getEntity(idReaderTicked).getRecords().size()==0) {
		//	throw new	WebApplicationException(Response.Status.NOT_FOUND);
//				throw new NoRecordsException("ServisBookRentalInfo - \r\n" + 
//						"no records");
		}
			} catch (NotRecordsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		
	
		
		
		
		/*for (RecordReaderTicket record : repReaderTicket.getEntity(idReaderTicked).getRecords()) {
			bookRentalInf = new BookRentalInfo();
			bookRentalInf.setRecord(record);
			bookRentalInf.setStatusRental(DateUtils.truncatedCompareTo(new Date(),
					DateUtils.addDays(record.getDateIssue(), 40), Calendar.DAY_OF_MONTH));	
			rentalInfoBooks.add(bookRentalInf);			
		}*/

		return rentalInfoBooks;
	}

}
