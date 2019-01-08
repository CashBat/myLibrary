package myLibrary.reposit.interfaces;

import java.util.Collection;

import myLibrary.model.Genre;

public interface BookRepository {

	public Collection<Genre> values();

	public void add(Genre item);

	public void edit(Genre item);

	public void delite(int id);
	
	public Genre getGenre(int id);
	
	public int  getID();
	
	public Collection<Genre> query(GenreSpecification specification);
	
}
