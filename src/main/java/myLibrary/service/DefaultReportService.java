package myLibrary.service;


import javax.ejb.Stateless;
import javax.inject.Inject;

import myLibrary.api.JasperGeneratorException;
import myLibrary.api.JasperReportGenerator;
import myLibrary.reposit.LibraryRepository;
import myLibrary.reposit.model.JasperPrintReport;
import myLibrary.reposit.qualifier.RepReport;
import myLibrary.service.interfasec.ReportService;
import myLibrary.service.report.ReportModelBuilder;
import myLibrary.service.report.qualifier.BuilderDeptInfo;

@Stateless
public class DefaultReportService implements ReportService {

	@Inject
	@RepReport
	LibraryRepository<JasperPrintReport> reportRep;

	@Inject
	JasperReportGenerator jasperReportGenerator;

	@Inject
	@BuilderDeptInfo
	ReportModelBuilder reportModelBuilder;

	@Override
	public JasperPrintReport getJasperPrintReport(int idReport) {
		JasperPrintReport jasperPrintReport = reportRep.getEntity(idReport);
		jasperReportGenerator.setParam(reportModelBuilder.build(), jasperPrintReport.getReportName());

		try {
			jasperReportGenerator.start();
			jasperPrintReport.setJasperPrint(jasperReportGenerator.getJasperPrint());
		} catch (JasperGeneratorException e) {
			throw e;
		}

		return jasperPrintReport;
	}

}
