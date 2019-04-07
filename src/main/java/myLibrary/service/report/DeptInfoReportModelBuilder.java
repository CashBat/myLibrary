package myLibrary.service.report;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import myLibrary.reposit.model.Book;
import myLibrary.reposit.model.Reader;
import myLibrary.reposit.model.ReaderTicket;
import myLibrary.reposit.model.RecordReaderTicket;
import myLibrary.service.interfasec.ReaderTicketService;
import myLibrary.service.report.model.StandardTemplateReportModel;
import myLibrary.service.report.model.table.BookDebtTableReportModel;
import myLibrary.service.report.qualifier.BuilderDeptInfo;

@BuilderDeptInfo
public class DeptInfoReportModelBuilder implements ReportModelBuilder {
	@Inject
	ReaderTicketService readerTicketService;

	@Override
	public ReportModel build() {
		StandardTemplateReportModel bookDebtReportModel = new StandardTemplateReportModel();
		bookDebtReportModel.setDateCreateReport(DateFormatUtils.format(DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH), "yyyy-MM-dd"));
		setDataTable(bookDebtReportModel);
		return bookDebtReportModel;
	}

	private void setDataTable(StandardTemplateReportModel bookDebtReportModel) {
		Book book;
		Reader reader;
		BookDebtTableReportModel bookDebt;
		int numb=0;
		for (ReaderTicket readerTicket : readerTicketService.getReaderTickets()) {
			List<RecordReaderTicket> debtBookRecords = readerTicketService.getDebtBookRecords(readerTicket);
			for (RecordReaderTicket record : debtBookRecords) {
				book = record.getBook();

				if (book == null) {
					throw new NullPointerException("null pointer: book");
				}

				reader = readerTicket.getReader();

				if (reader == null) {
					throw new NullPointerException("null pointer: reader");
				}

				bookDebt = new BookDebtTableReportModel();
				bookDebt.setNumber(numb ++);
				bookDebt.setIdBook(book.getId());
				bookDebt.setNameBook(book.getName());
				bookDebt.setDateIssue(DateFormatUtils.format(record.getDateIssue(), "yyyy-MM-dd"));
				bookDebt.setDayOverdue(
						Days.daysBetween(new DateTime(record.getDateIssue()), new DateTime(new Date())).getDays());
				bookDebt.setDebtorFIO(reader.getFio());
				bookDebt.setDebtorTel(reader.getTel());
				bookDebtReportModel.getDataTable().add(bookDebt);
			}
		}
	}
}
