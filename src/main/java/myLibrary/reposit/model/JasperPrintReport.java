package myLibrary.reposit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JasperPrint;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class JasperPrintReport extends AbstractLibraryModel {
	private String shortName;
	private String fullName;
	private String reportName;
	@JsonIgnore
	private JasperPrint jasperPrint;
}
