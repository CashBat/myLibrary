package myLibrary.model;

import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class RecordReaderTicket extends AbstractLibraryEntity {	
	private Book book;
	private Date dateIssue;
	private int quantityRentDay;
	private Date returnDate;

}
