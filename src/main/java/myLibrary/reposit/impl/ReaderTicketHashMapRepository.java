package myLibrary.reposit.impl;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;

import myLibrary.model.Reader;
import myLibrary.model.ReaderTicket;
import myLibrary.model.RecordReaderTicket;
import myLibrary.reposit.abs.AbstractHashMapLibraryRepository;
import myLibrary.reposit.annot.RepReader;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;
import myLibrary.reposit.interfaces.LibraryRepository;

@ApplicationScoped
@RepReaderTicket
public class ReaderTicketHashMapRepository extends AbstractHashMapLibraryRepository<Reader> {

	LibraryRepository<Reader> repReader;
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;

	public ReaderTicketHashMapRepository() {
	}

	public ReaderTicketHashMapRepository(@RepReader LibraryRepository<Reader> repReader,
			@RepRecordReaderTicket LibraryRepository<RecordReaderTicket> repRecordReaderTicket) {
		this.repReader = repReader;
		this.repRecordReaderTicket = repRecordReaderTicket;
		initReaderTicket();
	}

	private void initReaderTicket() {

		ReaderTicket readerTicket = new ReaderTicket();

		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(1));
		readerTicket.setRecords(new ArrayList<RecordReaderTicket>(
				Arrays.asList(repRecordReaderTicket.getEntity(1), repRecordReaderTicket.getEntity(2))));

		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(2));

		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(3));

		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(4));

	}

}
