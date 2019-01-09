package myLibrary.reposit.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import myLibrary.model.Genre;
import myLibrary.model.Reader;
import myLibrary.reposit.abs.AbstractHashMapLibraryRepository;
import myLibrary.reposit.annot.RepGenre;
import myLibrary.reposit.annot.RepReader;

@ApplicationScoped
@RepReader
public class ReaderHashMapRepository extends AbstractHashMapLibraryRepository<Reader> {

	@PostConstruct
	private void initReaderRep() {
		Reader reader = new Reader();
		reader.setId(getID());
		reader.setFIO("Кузьмин Макс");
		add(reader);
		reader = new Reader();
		reader.setId(getID());
		reader.setFIO("Жека Веселов");
		add(reader);
		reader = new Reader();
		reader.setId(getID());
		reader.setFIO("Артем Попов");
		add(reader);
		reader = new Reader();
		reader.setId(getID());
		reader.setFIO("Димка Багрей");
		add(reader);	
	}

}
