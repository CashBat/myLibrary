package myLibrary.reposit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import myLibrary.reposit.model.Reader;
import myLibrary.reposit.qualifier.RepReader;

@ApplicationScoped
@RepReader
public class ReaderLibraryRepository extends AbstractLibraryRepository<Reader> {

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
