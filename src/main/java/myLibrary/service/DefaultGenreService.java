package myLibrary.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import myLibrary.model.Genre;
import myLibrary.reposit.GenreRepository;

@Stateless
public class DefaultGenreService implements GenreService {
	@Inject
	GenreRepository repositoryGenre;

	@Override
	public Collection<Genre> getAllGenres() {
		return repositoryGenre.values();
	}
}
