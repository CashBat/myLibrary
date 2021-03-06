package myLibrary.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Book extends AbstractLibraryEntity {	
	private String name;
	private Genre genre;	
	private String description;
	private boolean availability;

}
