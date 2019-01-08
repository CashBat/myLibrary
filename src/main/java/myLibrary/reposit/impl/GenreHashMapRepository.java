package myLibrary.reposit.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import myLibrary.model.Genre;
import myLibrary.reposit.abs.AbstractHashMapLibraryRepository;
import myLibrary.reposit.annot.RepGenre;

@ApplicationScoped
@RepGenre
public class GenreHashMapRepository extends AbstractHashMapLibraryRepository<Genre> {

	@PostConstruct
	private void initGenreRep() {
		Genre genre = new Genre();
		genre.setId(getID());
		genre.setName("Драмма");
		add(genre);
		genre = new Genre();
		genre.setId(getID());
		genre.setName("Повесть");
		add(genre);
		genre = new Genre();
		genre.setId(getID());
		genre.setName("Рассказ");
		add(genre);
	}

}
