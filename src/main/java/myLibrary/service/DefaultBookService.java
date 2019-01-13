package myLibrary.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import myLibrary.entity.Book;
import myLibrary.entity.Genre;
import myLibrary.reposit.LibraryRepository;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepGenre;
import myLibrary.service.interfasec.BookService;
import myLibrary.service.specification.BookAvailabilitySpecification;

@Stateless
public class DefaultBookService implements BookService {
	@Inject
	@RepGenre
	LibraryRepository<Genre> repGenre;
	@Inject
	@RepBook
	LibraryRepository<Book> repBook;

	@Override
	public Collection<Genre> getAllGenres() {
		// return repGenre.query(new BookAvailabilitySpecification());
		return repGenre.values();
	}

	@Override
	public Collection<Book> getAllBooks() {
		return repBook.values();
	}

	public Collection<Book> getBooksAvailable() {
		return repBook.query(new BookAvailabilitySpecification(true));
	}

	public Collection<Book> getBooksOnHand() {
		return repBook.query(new BookAvailabilitySpecification(false));
	}
}
