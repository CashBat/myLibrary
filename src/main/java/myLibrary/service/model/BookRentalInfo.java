package myLibrary.service.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import myLibrary.entity.RecordReaderTicket;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class BookRentalInfo  {
	RecordReaderTicket record;
	Integer statusRental;
}
