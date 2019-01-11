package myLibrary.reposit.interfaces;

import java.util.Collection;

import myLibrary.model.LibraryEntity;
import myLibrary.service.specific.LibrarySpecification;

public interface LibraryRepository <T extends LibraryEntity> {

	public Collection<T> values();

	public void add(T item);

	public void edit(T item);

	public void delite(int id);
	
	public T getEntity(int id);
	
	public int  getID();
	
	public Collection<T> query(LibrarySpecification specification);
	


	
}
