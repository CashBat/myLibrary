package rep;

import java.util.Collection;

import myLibrary.Genre;

public interface GenreRepository {

	public Collection<Genre> values();

	public void add(Genre item);

	public void edit(Genre item);

	public void delite(Genre item);
	
	public Genre getGenre(int id);
	
	public Collection<Genre> query(GenreSpecification specification);
	


	
}
