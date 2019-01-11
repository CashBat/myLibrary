package myLibrary.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import myLibrary.model.Book;
import myLibrary.model.Genre;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepGenre;
import myLibrary.reposit.interfaces.LibraryRepository;
import myLibrary.service.specific.BookAvailabilitySpecification;
import myLibrary.service.specific.LibrarySpecification;

@Stateless
public class DefaultGenreService implements GenreService {
	@Inject
	@RepGenre
	LibraryRepository<Genre> repGenre;
	@Inject
	@RepBook
	LibraryRepository<Book> repBook;

	@Override
	public Collection<Genre> getAllGenres() {
		return repGenre.query(new BookAvailabilitySpecification());
		//return repGenre.values();
	}

	@Override
	public Collection<Book> getAllBooks() {

		return repBook.values();
	}
	
	public Collection<Book> getAvailabilityBooks(){
		return repBook.query(new BookAvailabilitySpecification());
	}
}
