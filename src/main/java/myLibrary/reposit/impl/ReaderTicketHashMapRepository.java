package myLibrary.reposit.impl;

import javax.enterprise.context.ApplicationScoped;

import myLibrary.model.Reader;
import myLibrary.model.ReaderTicket;
import myLibrary.reposit.abs.AbstractHashMapLibraryRepository;
import myLibrary.reposit.annot.RepReader;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.interfaces.LibraryRepository;

@ApplicationScoped
@RepReaderTicket
public class ReaderTicketHashMapRepository extends AbstractHashMapLibraryRepository<Reader> {

	LibraryRepository<Reader> repReader;

	public ReaderTicketHashMapRepository() {
	}

	public ReaderTicketHashMapRepository(@RepReader LibraryRepository<Reader> repReader) {
		this.repReader = repReader;
		initReaderTicket();
	}

	private void initReaderTicket() {
		
		ReaderTicket readerTicket = new ReaderTicket();

		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(1));
		
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(2));
		
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(3));
		
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(4));
		
	}

}
