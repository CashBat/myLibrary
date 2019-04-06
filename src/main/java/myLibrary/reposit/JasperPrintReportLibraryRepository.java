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
		add(createReport("Архив","Полный реестр книг, числящихся в библиотеке (в том числе и «на руках»)","debtInfo"));	
		add(createReport("Топ","Книги которые брали больше двух раз за текущий год","dafdsf"));
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