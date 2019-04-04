package myLibrary.reposit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Book extends AbstractLibraryModel {	
	private String name;
	private Genre genre;	
	private String description;
	private boolean availability;
	
	@Override
	public String toString() {
		return "Book [name=" + name + ", genre=" + genre + ", description=" + description + ", availability="
				+ availability + "]";
	}
	
}
