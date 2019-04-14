package myLibrary.reposit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import myLibrary.reposit.model.JasperPrintReport;
import myLibrary.reposit.qualifier.RepReport;

@ApplicationScoped
@RepReport
public class JasperPrintReportLibraryRepository extends AbstractLibraryRepository<JasperPrintReport> {

	@PostConstruct
	private void initGenreRep() {
		
		add(createReport("Задолженность","Учет должников, персональные данные, списки книг","debtInfo"));	
		add(createReport("Рейтинг","Популярные книги за текущий год","reating"));
		add(createReport("Фейк1","Фейковый отчет номер 1","dafdsf"));
		add(createReport("Фейк1","Фейковый отчет номер 1",null));
		add(createReport("Фейк2","Фейковый отчет номер 2",""));
		
	}
	
	private JasperPrintReport createReport(String shortName, String fullName, String reportName) {
		JasperPrintReport report = new JasperPrintReport();
		report.setId(getID());
		report.setShortName(shortName);
		report.setFullName(fullName);
		report.setReportName(reportName);
		return report;
	}

}
