package myLibrary.reposit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import myLibrary.reposit.annot.RepReport;
import myLibrary.reposit.model.Report;

@ApplicationScoped
@RepReport
public class ReportHashMapRepository extends AbstractHashMapLibraryRepository<Report> {

	@PostConstruct
	private void initGenreRep() {
		
		add(createReport("Задолженность","Учет должников, персональные данные, списки книг"));	
		add(createReport("Архив","Полный реестр книг, числящихся в библиотеке (в том числе и «на руках»)"));	
		add(createReport("Топ","Книги которые брали больше двух раз за текущий год"));
		add(createReport("Фейк1","Фейковый отчет номер 1"));
		add(createReport("Фейк2","Фейковый отчет номер 2"));
		
	}
	
	private Report createReport(String shortName, String fullName) {
		Report report = new Report();
		report.setId(getID());
		report.setShortName(shortName);
		report.setFullName(fullName);	
		return report;
	}

}