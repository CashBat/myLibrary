package myLibrary.reposit.model;

public abstract class AbstractLibraryModel implements LibraryModel {

	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
