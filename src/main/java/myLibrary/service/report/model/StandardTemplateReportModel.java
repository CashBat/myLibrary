package myLibrary.service.report.model;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import myLibrary.service.report.ReportModel;
import myLibrary.service.report.model.table.TableReportModel;



@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class StandardTemplateReportModel implements ReportModel {
	private String dateCreateReport;
	private List<TableReportModel> dataTable = new ArrayList<>();
}
