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
		reader.setFio("Кузьмин Макс");
		reader.setTel("89043794344");
		add(reader);
		reader = new Reader();
		reader.setId(getID());
		reader.setFio("Жека Веселов");
		reader.setTel("89078686868");
		add(reader);
		reader = new Reader();
		reader.setId(getID());
		reader.setFio("Артем Попов");
		reader.setTel("806967950679");
		add(reader);
		reader = new Reader();
		reader.setId(getID());
		reader.setFio("Димка Багрей");
		reader.setTel("856789569875");
		add(reader);	
	}

}
