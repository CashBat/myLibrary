package myLibrary.service.interfasec;

import java.util.Collection;

import myLibrary.entity.Book;
import myLibrary.entity.Genre;

public interface BookService {

	public Collection<Genre> getAllGenres();

	public Collection<Book> getAllBooks();

	public Collection<Book> getBooksAvailable();
	
	public Collection<Book> getBooksOnHand();

}
