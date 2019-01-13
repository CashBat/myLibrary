package myLibrary.reposit;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import myLibrary.entity.Reader;
import myLibrary.entity.ReaderTicket;
import myLibrary.entity.RecordReaderTicket;
import myLibrary.reposit.annot.RepReader;
import myLibrary.reposit.annot.RepReaderTicket;
import myLibrary.reposit.annot.RepRecordReaderTicket;

@ApplicationScoped
@RepReaderTicket
public class ReaderTicketHashMapRepository extends AbstractHashMapLibraryRepository<ReaderTicket> {

	LibraryRepository<Reader> repReader;
	LibraryRepository<RecordReaderTicket> repRecordReaderTicket;

	public ReaderTicketHashMapRepository() {
	}

	@Inject
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
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(2));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(3));
		add(readerTicket);

		readerTicket = new ReaderTicket();
		readerTicket.setId(getID());
		readerTicket.setReader(repReader.getEntity(4));
		readerTicket.setRecords(new ArrayList<RecordReaderTicket>(Arrays.asList(repRecordReaderTicket.getEntity(3))));
		add(readerTicket);

	}

}
