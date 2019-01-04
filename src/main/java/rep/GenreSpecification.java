package rep;

import java.util.Collection;

import myLibrary.Genre;

public interface GenreSpecification {
	Collection<Genre> satisfiedBy(Collection<Genre> books);
}
