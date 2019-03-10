package myLibrary.service.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Rental {
	private int idRecordRiderTicket;
	private int idRiderTicket;
	private int idBook;
	private String dateIssue;
	private int quantityRentDay;
	private String returnDate;
	private Integer statusRental;
}
