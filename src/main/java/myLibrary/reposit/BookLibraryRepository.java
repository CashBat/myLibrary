package myLibrary.reposit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import myLibrary.reposit.model.Book;
import myLibrary.reposit.model.Genre;
import myLibrary.reposit.qualifier.RepBook;
import myLibrary.reposit.qualifier.RepGenre;

@ApplicationScoped
@RepBook
public class BookLibraryRepository extends AbstractLibraryRepository<Book> {

	LibraryRepository<Genre> repositoryGenre;

	public BookLibraryRepository() {
	}

	@Inject
	public BookLibraryRepository(@RepGenre LibraryRepository<Genre> repositoryGenre) {
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
