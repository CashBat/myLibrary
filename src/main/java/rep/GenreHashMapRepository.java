package rep;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import myLibrary.Genre;

public class GenreHashMapRepository  implements GenreRepository{
	
	private AtomicInteger ids = new AtomicInteger();
	private final HashMap<Integer, Genre> geners = new HashMap<Integer, Genre>();

	@Override
	public Collection<Genre> values() {
		// TODO Auto-generated method stub
	 return geners.values();
	}

	@Override
	public void add(Genre item) {
		geners.put(ids.incrementAndGet(), item);
	
}

	@Override
	public void edit(Genre item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delite(Genre item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Genre getGenre(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Genre> query(GenreSpecification specification) {
		// TODO Auto-generated method stub
		return null;
	}

}
