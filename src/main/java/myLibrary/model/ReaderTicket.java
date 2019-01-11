package myLibrary.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class ReaderTicket extends AbstractLibraryEntity {
	private Reader reader;

}
