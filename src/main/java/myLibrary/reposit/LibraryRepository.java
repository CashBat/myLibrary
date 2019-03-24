package myLibrary.reposit;

import java.util.Collection;

import myLibrary.entity.LibraryEntity;
import myLibrary.service.specification.interfase.LibrarySpecification;

public interface LibraryRepository<T extends LibraryEntity> {

	public Collection<T> values();

	public void add(T item);

	public void update(T item);

	public void delite(int id);

	public T getEntity(int id);

	public int getID();

	public Collection<T> query(LibrarySpecification specification);

}
