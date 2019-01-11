package myLibrary.service;

import java.util.Collection;

import myLibrary.model.Book;
import myLibrary.model.Genre;

public interface GenreService {

	public Collection<Genre> getAllGenres();
	
	public Collection<Book> getAllBooks();
	
	public Collection<Book> getAvailabilityBooks();


}
