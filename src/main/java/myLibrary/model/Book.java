package myLibrary.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Book extends AbstractLibraryEntity {	

	private Genre genre;	
	private String description;
	private boolean availability;

}
