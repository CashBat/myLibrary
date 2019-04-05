package myLibrary.service.report.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class TemplateReport<T> {

	private Date dateCreateReport;
	private List<T> dataTable = new ArrayList<>();


	
	public TemplateReport(Date dateCreateReport, List<T> dataTable) {
		super();
		this.dateCreateReport = dateCreateReport;
		this.dataTable = dataTable;
	}

	

}
