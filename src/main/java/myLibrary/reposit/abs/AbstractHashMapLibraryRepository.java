
package myLibrary.reposit.abs;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import myLibrary.model.LibraryEntity;
import myLibrary.reposit.interfaces.GenreSpecification;
import myLibrary.reposit.interfaces.LibraryRepository;

public abstract class AbstractHashMapLibraryRepository<T extends LibraryEntity> implements LibraryRepository<T> {

	private AtomicInteger ids = new AtomicInteger();
	private final HashMap<Integer, T> geners = new HashMap<Integer, T>();

	public Collection<T> values() {
		return geners.values();
	}

	public void add(T item) {
		geners.put(item.getId(), item);
	}

	public void edit(T item) {
		geners.replace(item.getId(), item);

	}

	public void delite(int id) {
		geners.remove(id);
	}

	public T getEntity(int id) {
		return geners.get(id);
	}

	
	  public Collection<T> query(GenreSpecification specification) {
	  
		return null/* specification.satisfiedBy(geners.values()) */; }
	 

	public int getID() {
		return ids.incrementAndGet();
	}

}
