package myLibrary.service.interfasec;

import net.sf.jasperreports.engine.JasperPrint;

public interface ReportService {
	
	JasperPrint getReport(int idReport);
	
}
