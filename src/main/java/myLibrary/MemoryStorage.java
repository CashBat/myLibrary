package myLibrary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.inject.Named;


@Singleton
public class MemoryStorage {
	@Inject
	@Named("bookMemoryCell")
	private MemoryCell instBook;
	@Inject
	@Named("genreMemoryCell")
	private MemoryCell instGenre;

	public MemoryCell getInstBook() {
		return instBook;
	}

	public MemoryCell getInstGenre() {
		return instGenre;
	}

	@PostConstruct
	public void createStartInfo() {
		createGenres();
		createBooks();
	
	}

	private void createGenres() {
		Genre genre;
		genre = new Genre();
		genre.setTitle("Драмма");
		genre.setId(instGenre.getId());
		instGenre.add(genre);
	}

	private void createBooks() {
		LibraryBook lbook;
		lbook = new LibraryBook();
		lbook.setTitle("Жизнь в одиноком ключе");
		lbook.setDescription("История о рыбаке проживающем на берегу реки, и переживающим все её волнения");
		lbook.setAvailability(true);
		lbook.setGenre((Genre) instGenre.get(1));
		lbook.setId(instBook.getId());
		instBook.add(lbook);
		
		lbook = new LibraryBook();
		lbook.setTitle("Колобок");
		lbook.setDescription("История о том как наеба..ть всех, на сколько мозгов хватит");
		lbook.setAvailability(true);
		lbook.setGenre((Genre) instGenre.get(1));
		lbook.setId(instBook.getId());
		instBook.add(lbook);

	}

}
