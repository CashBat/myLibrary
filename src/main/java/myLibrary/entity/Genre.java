package myLibrary.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Genre extends AbstractLibraryEntity {
	private String name;

}
