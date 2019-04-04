package myLibrary.service.interfasec;

import java.util.Collection;

import myLibrary.reposit.model.Book;
import myLibrary.reposit.model.Genre;

public interface BookService {

	public Collection<Genre> getAllGenres();

	public Collection<Book> getAllBooks();

	public Collection<Book> getBooksAvailable();
	
	public Collection<Book> getBooksOnHand();
	
	public Book getBook(int bookID);
	
	public void closeAccess(Book bookID) ;
	public void openAccess(Book bookID) ;
}
