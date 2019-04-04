package myLibrary.reposit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Genre extends AbstractLibraryModel {
	private String name;

}
