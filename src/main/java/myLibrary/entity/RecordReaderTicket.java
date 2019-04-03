package myLibrary.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private ReaderTicket readerTicket;
	@Override
	public String toString() {
		return "RecordReaderTicket [book=" + book + ", dateIssue=" + dateIssue + ", quantityRentDay=" + quantityRentDay
				+ ", returnDate=" + returnDate + ", readerTicket=" + readerTicket + "]";
	} 
	

}
