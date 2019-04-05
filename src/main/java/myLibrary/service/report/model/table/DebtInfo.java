package myLibrary.service.report.model.table;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class DebtInfo {
	private int number ;
	private int idBook;
	private String nameBook;
	private Date dateIssue;
	private int dayOverdue;
	private String debtorFIO;
	private String debtorTell;
	
	public DebtInfo() {}
	
	public DebtInfo(int number, int idBook, String nameBook, Date dateIssue, int dayOverdue, String debtorFIO,
			String debtorTell) {
		super();
		this.number = number;
		this.idBook = idBook;
		this.nameBook = nameBook;
		this.dateIssue = dateIssue;
		this.dayOverdue = dayOverdue;
		this.debtorFIO = debtorFIO;
		this.debtorTell = debtorTell;
	}
	
	
}
