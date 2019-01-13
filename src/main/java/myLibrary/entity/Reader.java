package myLibrary.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Reader extends AbstractLibraryEntity {
	private String fio;
	private String tel;

}
