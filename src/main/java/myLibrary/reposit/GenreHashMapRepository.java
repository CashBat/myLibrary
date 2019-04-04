package myLibrary.reposit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

import myLibrary.reposit.annot.RepGenre;
import myLibrary.reposit.model.Genre;

@RequestScoped
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
