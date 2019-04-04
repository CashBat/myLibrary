package myLibrary.reposit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Report extends AbstractLibraryModel {
	private String shortName;
	private String fullName;	
}
