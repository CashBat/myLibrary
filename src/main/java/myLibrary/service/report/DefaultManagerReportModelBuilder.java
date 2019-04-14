package myLibrary.service.report;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import myLibrary.service.report.exception.NotFoundReportModelBuilderException;
import myLibrary.service.report.qualifier.BuilderDeptInfo;
import myLibrary.service.report.qualifier.BuilderRatingInfo;

@RequestScoped
public class DefaultManagerReportModelBuilder implements ManagerReportModelBuilder {

	@Inject
	@Any
	Instance<ReportModelBuilder> instanceReportModelBuilder;

	public ReportModelBuilder getReportModelBuilder(int idReport) {
		switch (idReport) {
		case 1:
			return instanceReportModelBuilder.select(new DeptInfoReportModelBuilderQualifier()).get();
		case 2:
			return instanceReportModelBuilder.select(new ReatingInfoReportModelBuilderQualifier()).get();
		default:
			throw new NotFoundReportModelBuilderException(idReport);
		}
	}

	private static class DeptInfoReportModelBuilderQualifier extends AnnotationLiteral<BuilderDeptInfo> {
		private static final long serialVersionUID = -4074967476212789621L;

	}

	private static class ReatingInfoReportModelBuilderQualifier extends AnnotationLiteral<BuilderRatingInfo> {
		private static final long serialVersionUID = -5779116209568124493L;

	}
}
