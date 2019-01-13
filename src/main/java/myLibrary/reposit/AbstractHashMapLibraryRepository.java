
package myLibrary.reposit;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import myLibrary.entity.LibraryEntity;
import myLibrary.service.specification.interfase.LibrarySpecification;

public abstract class AbstractHashMapLibraryRepository<T extends LibraryEntity> implements LibraryRepository<T> {

	private AtomicInteger ids = new AtomicInteger();
	private final HashMap<Integer, T> libraryEntits = new HashMap<Integer, T>();

	public Collection<T> values() {
		return libraryEntits.values();
	}

	public void add(T item) {
		libraryEntits.put(item.getId(), item);
	}

	public void edit(T item) {
		libraryEntits.replace(item.getId(), item);

	}

	public void delite(int id) {
		libraryEntits.remove(id);
	}

	public T getEntity(int id) {
		return libraryEntits.get(id);
	}

	public Collection<T> query(LibrarySpecification specification) {
		return specification.satisfiedBy(values());
	}

	public int getID() {
		return ids.incrementAndGet();
	}

}
