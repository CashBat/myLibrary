package myLibrary.service;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import myLibrary.api.JasperGeneratorException;
import myLibrary.api.JasperReportGenerator;
import myLibrary.service.interfasec.ReportService;
import myLibrary.service.report.model.TemplateReport;
import myLibrary.service.report.model.table.DebtInfo;
import net.sf.jasperreports.engine.JasperPrint;


@Stateless
public class GeneralReportService implements ReportService  {
	
	@Inject
	JasperReportGenerator jasperReportGenerator;

	@Override
	public JasperPrint getReport(int idReport) {
		List<DebtInfo> list =Arrays.asList(new DebtInfo(1,2,"Колобок",new Date(),1,"Макгрегор","71-21-58"), new DebtInfo(2,1,"Репка",new Date(),2,"РиттаСкитер","71-34-04"));
		
		jasperReportGenerator.setParam(new TemplateReport<DebtInfo>(new Date(), list), "debtInfo");		
		
		try {
			jasperReportGenerator.start();
		}catch (JasperGeneratorException e) {
			throw e;
		}
	
		return jasperReportGenerator.getJasperPrint();
	}

	

	
	
}
