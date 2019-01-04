package myLibrary;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Singleton;
import javax.inject.Named;


@Singleton
@Named("bookMemoryCell")
public class BookMemoryCell implements MemoryCell {

	private AtomicInteger ids = new AtomicInteger();
	private final HashMap<Integer, LibraryBook> memoryCell = new HashMap<Integer, LibraryBook>();
	
	@Override
	public Collection<LibraryBook> values() {
		return memoryCell.values();
	}
	@Override
	public void add(MemoryItem item) {
		if(item instanceof LibraryBook) {
		LibraryBook lb = (LibraryBook) item;
		memoryCell.put(lb.getId(), lb);
		}
	}
	@Override
	public   void  edit(MemoryItem item) {		
		if(item instanceof LibraryBook) {
			LibraryBook lb = (LibraryBook) item;
			memoryCell.replace(lb.getId(), lb);
			}			
	}
	
	@Override
	public void delete(int id) {
		memoryCell.remove(id);
	}
	@Override
	public LibraryBook get(int id) {
		return memoryCell.get(id);
	}
	@Override
	public int getId() {
		return ids.incrementAndGet();
	}



	
	

}
