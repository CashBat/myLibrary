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
		record.setReaderTicket(this);
		records.add(record);
	}

	public void removeRecord(RecordReaderTicket record) {
		records.remove(record);
		record.setReaderTicket(null);
	}

	public RecordReaderTicket getRecord(int idRecordRiderTicket) {
		for (RecordReaderTicket record : records) {

			if (record.getId() == idRecordRiderTicket) {
				return record;
			}

		}
		return null;
	}

}
