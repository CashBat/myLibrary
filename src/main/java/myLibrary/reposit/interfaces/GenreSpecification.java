package myLibrary.reposit.interfaces;

import java.util.Collection;

import myLibrary.model.Genre;

public interface GenreSpecification {
	Collection<Genre> satisfiedBy(Collection<Genre> genres);
}
