package myLibrary.model;

public abstract class AbstractLibraryEntity implements LibraryEntity {

	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
