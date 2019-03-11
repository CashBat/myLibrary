package myLibrary.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class ReaderTicket extends AbstractLibraryEntity {
	private Reader reader;
	private List<RecordReaderTicket> records = new ArrayList<RecordReaderTicket>();


	public void addRecord(RecordReaderTicket record) {
		records.add(record);
		record.setReaderTicket(this);
	}

	public void removeRecord(RecordReaderTicket record) {
		records.remove(record);
		record.setReaderTicket(null);
	}

}
