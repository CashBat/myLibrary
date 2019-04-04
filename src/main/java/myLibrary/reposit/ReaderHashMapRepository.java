package myLibrary.reposit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

import myLibrary.reposit.annot.RepReader;
import myLibrary.reposit.model.Reader;

@RequestScoped
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
