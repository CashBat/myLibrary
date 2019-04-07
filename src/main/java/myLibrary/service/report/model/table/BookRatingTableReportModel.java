package myLibrary.service.report.model.table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class BookRatingTableReportModel implements TableReportModel {
	private int number ;
	private int idBook;
	private String nameBook;
	private int rating;
}
