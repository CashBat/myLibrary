package myLibrary.service.report.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import myLibrary.service.report.ReportModel;
import myLibrary.service.report.model.table.TableReportModel;



@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class StandardTemplateReportModel implements ReportModel {
	private String dateCreateReport=DateFormatUtils.format(DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH), "yyyy-MM-dd");
	private List<TableReportModel> dataTable = new ArrayList<>();
}
