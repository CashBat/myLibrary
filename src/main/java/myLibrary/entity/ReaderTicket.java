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

		if (record.getReaderTicket() != null) {
			record.setReaderTicket(null);
		} else {			
			records.remove(record);	
		}

	}

	public RecordReaderTicket getRecord(int idRecordRiderTicket) {
		for (RecordReaderTicket record : records) {

			if (record.getId() == idRecordRiderTicket) {
				return record;
			}

		}
		return null;
	}
	
	@Override
	public String toString() {
		return "ReaderTicket [reader=" + reader + ", records=" + records + "]";
	}

}
