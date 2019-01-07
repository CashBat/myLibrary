package myLibrary.reposit;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;

import myLibrary.model.Genre;

@ApplicationScoped
public class GenreHashMapRepository implements GenreRepository {

	private AtomicInteger ids = new AtomicInteger();
	private final HashMap<Integer, Genre> geners = new HashMap<Integer, Genre>();
	
	@PostConstruct
	 public void init() {
		Genre genre = new Genre();
		genre.setId(getID());
		genre.setTitle("Драмма");
		add(genre);
		genre = new Genre();
		genre.setId(getID());
		genre.setTitle("Повесть");
		add(genre);
		genre = new Genre();
		genre.setId(getID());
		genre.setTitle("Рассказ");
		add(genre);
	 }

	@Override
	public Collection<Genre> values() {
		return geners.values();
	}

	@Override
	public void add(Genre item) {
		geners.put(item.getId(), item);
	}

	@Override
	public void edit(Genre item) {
		geners.replace(item.getId(), item);

	}

	@Override
	public void delite(int id) {
		geners.remove(id);
	}

	@Override
	public Genre getGenre(int id) {
		return geners.get(id);
	}

	@Override
	public Collection<Genre> query(GenreSpecification specification) {
		return specification.satisfiedBy(geners.values());
	}

	@Override
	public int getID() {
		return ids.incrementAndGet();
	}

}
