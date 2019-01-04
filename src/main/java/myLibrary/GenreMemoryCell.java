package myLibrary;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Singleton;
import javax.inject.Named;


@Singleton
@Named("genreMemoryCell")
public class GenreMemoryCell implements MemoryCell {
	
	private AtomicInteger ids = new AtomicInteger();
	private final HashMap<Integer, Genre> memoryCell = new HashMap<Integer, Genre>();


	@Override
	public Collection<Genre> values() {
		return memoryCell.values();
	}
	@Override
	public void add(MemoryItem item) {
		if(item instanceof Genre) {
			Genre lb = (Genre) item;
		memoryCell.put(lb.getId(), lb);
		}
	}
	@Override
	public   void  edit(MemoryItem item) {
		
		if(item instanceof Genre) {
			Genre lb = (Genre) item;
			memoryCell.replace(lb.getId(), lb);
			}
			
	}
	@Override
	public void delete(int id) {
		memoryCell.remove(id);
	}
	@Override
	public Genre get(int id) {
		return memoryCell.get(id);
	}
	@Override
	public int getId() {
		return ids.incrementAndGet();
	}

}