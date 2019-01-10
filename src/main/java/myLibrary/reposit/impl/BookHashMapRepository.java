package myLibrary.reposit.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import myLibrary.model.Book;
import myLibrary.model.Genre;
import myLibrary.reposit.abs.AbstractHashMapLibraryRepository;
import myLibrary.reposit.annot.RepBook;
import myLibrary.reposit.annot.RepGenre;
import myLibrary.reposit.interfaces.LibraryRepository;

@ApplicationScoped
@RepBook
public class BookHashMapRepository extends AbstractHashMapLibraryRepository<Book> {

	LibraryRepository<Genre> repositoryGenre;

	public BookHashMapRepository() {
	}

	@Inject
	public BookHashMapRepository(@RepGenre LibraryRepository<Genre> repositoryGenre) {
		this.repositoryGenre = repositoryGenre;
		initBookRep();
	}

	private void initBookRep() {
		Book book = new Book();
		book.setId(getID());
		book.setName("Колобок");
		book.setGenre(repositoryGenre.getEntity(1));
		book.setDescription("Что будет если много выделываться");
		book.setAvailability(true);
		add(book);

		book = new Book();
		book.setId(getID());
		book.setName("Колобок");
		book.setGenre(repositoryGenre.getEntity(1));
		book.setDescription("Что будет если много выделываться");
		book.setAvailability(false);
		add(book);

		book = new Book();
		book.setId(getID());
		book.setName("Репка");
		book.setGenre(repositoryGenre.getEntity(2));
		book.setDescription("Вместе мы веник!!");
		book.setAvailability(true);
		add(book);

		book = new Book();
		book.setId(getID());
		book.setName("Золотая рыбка");
		book.setGenre(repositoryGenre.getEntity(2));
		book.setDescription("Нужно знать меру");
		book.setAvailability(true);
		add(book);

		book = new Book();
		book.setId(getID());
		book.setName("Ejb в действие");
		book.setGenre(repositoryGenre.getEntity(3));
		book.setDescription("Нелохо о том, как JavaЕЕ код писать");
		book.setAvailability(true);
		add(book);
	}

}