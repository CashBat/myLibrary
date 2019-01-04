package myLibrary;

import java.util.Collection;
import java.util.HashMap;


public interface MemoryCell  {
	
		public <T> Collection<T> values();

		public void edit(MemoryItem item);

		public void delete(int id);

		public  <T> T  get(int id);
		
		public int  getId();

		public void add(MemoryItem item);
		
}
