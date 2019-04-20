package myLibrary.service.report;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;

import myLibrary.reposit.model.Book;
import myLibrary.reposit.model.RecordReaderTicket;
import myLibrary.service.interfasec.ReaderTicketService;
import myLibrary.service.report.model.StandardTemplateReportModel;
import myLibrary.service.report.model.table.BookRatingTableReportModel;
import myLibrary.service.report.model.table.TableReportModel;
import myLibrary.service.report.qualifier.BuilderRatingInfo;

@BuilderRatingInfo
public class RatingInfoReportModelBuilder implements ReportModelBuilder {
	
	@Inject
	private ReaderTicketService readerTicketService;
	private final Integer STARTRATING =1;

	@Override
	public ReportModel build() {
		StandardTemplateReportModel bookRatingReportModel = new StandardTemplateReportModel();
		setDataTable(bookRatingReportModel);
		return bookRatingReportModel;
	}

	private void setDataTable(StandardTemplateReportModel bookRatingReportModel) {
		

		int numb = 0;

		List<RecordReaderTicket> recordsThisYear = readerTicketService.getRecordReaderTickets().stream()
				.filter(record -> DateUtils.truncatedEquals(record.getDateIssue(), new Date(), Calendar.YEAR))
				.collect(Collectors.toList());

//		
//		Map<Book, Long> phonesByCompany = recordsThisYear.stream().collect(
//		        Collectors.groupingBy(RecordReaderTicket::getBook, Collectors.counting()));

//		for(Map.Entry<String, Long> item : phonesByCompany.entrySet()){
//		 
//		    System.out.println(item.getKey() + " - " + item.getValue());
//		}

//		Set<Book> unique = new HashSet<>();
//		
//		Set<Book> duplicates = new HashSet<>();
//
//		
//		for(RecordReaderTicket record : recordsThisYear ) {
//			book=record.getBook();
//			
//			if (book == null) {
//				throw new NullPointerException("null pointer: book");
//			}
//			
//		    if( !unique.add(record.getBook()) ) {
//		    	
//		    	 if( !duplicates.add(record.getBook()) ) { // уже добовлялся?
//		    	
//		   
//		    	
//		    	
//				    	
//				    	
//				    	
//				    } else {
//				    	
//				     	bookRating =new BookRatingTableReportModel();
//				    	bookRating.setNumber(numb++);
//				    	bookRating.setIdBook(book.getId());
//				    	bookRating.setNameBook(book.getName());
//				    	bookRating.setRating(2);
//				    	bookRatingReportModel.getDataTable().add(bookRating);
//				    	//duplicates.add(record.getBook());	
//				    }
//		    	 
//		    	 
//		    	
//		    	
//		    }

//		for (RecordReaderTicket record : recordsThisYear) {
//			book = record.getBook();
//
//			if (book == null) {
//				throw new NullPointerException("null pointer: book");
//			}
//			
//			
//			int iterator = 0;
//			do {
//
//				bookRating = (BookRatingTableReportModel) bookRatingReportModel.getDataTable().get(iterator);
//
//				if (book.getId() != bookRating.getIdBook()) {
//					bookRating = new BookRatingTableReportModel();
//					bookRating.setNumber(numb++);
//					bookRating.setIdBook(book.getId());
//					bookRating.setNameBook(book.getName());
//					bookRating.setRating(1);
//					bookRatingReportModel.getDataTable().add(bookRating);
//				} else {
//					bookRating.setRating(bookRating.getRating() + 1);
//				}
//			
//				iterator++;
//			}
//
//			while (iterator == bookRatingReportModel.getDataTable().size());
//
//		}
		
		
	

		for (RecordReaderTicket record : recordsThisYear) {
			Book book = record.getBook();

			if (book == null) {
				throw new NullPointerException("null pointer: book");
			}
			
		
				
			
				if(!isUpRating(book,bookRatingReportModel)) {
					bookRatingReportModel.getDataTable().add(createRating(++numb,book));
				}

			}
		}
		
		
		

//	Set<Book> unique = new HashSet<>();
//		
//		List<Book> duplicates = new ArrayList<>();
//		for(RecordReaderTicket record : recordsThisYear ) {
//			for(Book bookDuplicat:duplicates) {
//				if(bookDuplicat==record.getBook()) {
//					
//				}
//				
//			}
//			
//			
//		}

	

	private boolean isUpRating(Book book, StandardTemplateReportModel bookRatingReportModel) {
		int sizeDataTable = bookRatingReportModel.getDataTable().size();
		for ( int i=0; i<=sizeDataTable-1;i++) { 
			BookRatingTableReportModel	bookRating = (BookRatingTableReportModel) bookRatingReportModel.getDataTable().get(i);
			if (book.getId() == bookRating.getIdBook()) {				
				bookRating.setRating(bookRating.getRating() + 1);
		return true;	
			} 
		}
		return false;
	}

	private TableReportModel createRating(int numb, Book book) {
		BookRatingTableReportModel	bookRating = new BookRatingTableReportModel();
		bookRating.setNumber(numb);
		bookRating.setIdBook(book.getId());
		bookRating.setNameBook(book.getName());
		bookRating.setRating(STARTRATING);
		return bookRating;
	}


	
}
