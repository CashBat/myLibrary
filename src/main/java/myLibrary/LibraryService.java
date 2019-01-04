package myLibrary;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;




@Stateless
@Local(LibraryService.class)
public class LibraryService {
	
	  @Inject
	  MemoryStorage repo;

	public void createBook () {
		
	}
	
	
@Produces
public Aftor getAftor() {
	Aftor aftor = new Aftor();
	aftor.setMessage("Привет я в скойпе");
	return aftor;
}
	
public Collection<MemoryItem> getBooks () {
	
	
return repo.getInstBook().values();
		
	}

public void getAddBooks () {
	
}

public void getBooksForId () {
	
}
	
	

}
