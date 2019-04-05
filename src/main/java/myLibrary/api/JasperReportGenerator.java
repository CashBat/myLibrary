package myLibrary.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperReportGenerator {
	private List<Object> reportModels;
	private String nameJRFile;
	private List<String> notNullParams;
	private boolean isPrintReport;
	private List<Map<String, Object>> listMap;
	private List<JasperPrint> jpList;

	/**
	 * задает параметры для группы оточетов: 1) группа моделей отчета с заполненными
	 * полями; 2) имя файла jrxml Шаблона(должны хранится в директории
	 * -resources\jasper\.
	 */
	public void setParam(List<Object> reportModels, String nameJRFile) {
		this.reportModels = reportModels;
		this.nameJRFile = nameJRFile;
	}

	/**
	 * задает параметры для одного отчета: 1) модель отчета с заполненными полями;
	 * 2) имя файла jrxml Шаблона(должны хранится в директории -resources\jasper\.
	 */
	public void setParam(Object reportModel, String nameJRFile) {
		reportModels = new ArrayList<Object>();
		reportModels.add(reportModel);
		this.nameJRFile = nameJRFile;
	}

	/**
	 * notNullParams - список параметров которые не должны быть пустыми. Если
	 * обязательные параметры пустые, вызывается иключение.
	 */
	public void setNotNullParams(List<String> notNullParams) {
		this.notNullParams = notNullParams;
	}

	/**
	 * возвращает группу сгенерированных отчетов, если в параметрах было заданно
	 * несколько моделей;
	 */
	public List<JasperPrint> getJasperPrints() {
		return jpList;
	}

	/**
	 * возвращает сгенерированный отчет, модель которого была задана в параметрах;
	 */
	public JasperPrint getJasperPrint() {
		return jpList.get(0);
	}

	public void start() {

		if (reportModels == null) {

			throw new JasperGeneratorException("null param:reportModel");
		}

		if (nameJRFile == null) {

			throw new JasperGeneratorException("null param: nameJRFile");
		}

		isPrintReport = true;
		setListMap(reportModels);
		if (!isPrintReport) {

			throw new JasperGeneratorException("missing required parameters");
		}

		printJReport();
	}

	private JasperReport getCompiledJrxmlFile() throws JRException {
		JasperDesign design = JRXmlLoader.load(getClass().getResourceAsStream("/jasper/" + nameJRFile + ".jrxml"));
		JRDesignStyle defaultStyle = new JRDesignStyle();
		defaultStyle.setName("default_style");
		defaultStyle.setFontName("Arial Narrow");
		defaultStyle.setDefault(true);
		design.addStyle(defaultStyle);
		design.setDefaultStyle(defaultStyle);
		return JasperCompileManager.compileReport(design);
	}

	private void printJReport() {
		try {
			jpList = new ArrayList<JasperPrint>();
			for (Map<String, Object> params : listMap) {
				JasperPrint print;
				print = JasperFillManager.fillReport(getCompiledJrxmlFile(), params, new JREmptyDataSource());
				jpList.add(print);
			}
		} catch (Exception e) {
			throw new JasperGeneratorException("error when filling out the report");
		}
	}

	/**
	 * Установка параметров через свойства объекта. objectParam - объект с
	 * заполненными полями.
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */

	private void setListMap(List<Object> lstObjectParam) {
		try {
			listMap = new ArrayList<Map<String, Object>>();
			for (Object objectParam : lstObjectParam) {
				Map<String, Object> params = new HashMap<String, Object>();
				String nameParam = null;
				String valueParam = null;
				List<Object> tableData = new ArrayList<>();
				Boolean isNullParams;
				Field[] fields = objectParam.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);

					if (field.get(objectParam) != null) {

						isNullParams = false;
						nameParam = field.getName();

						if (field.get(objectParam) instanceof Collection) {

							tableData.addAll((Collection<?>) field.get(objectParam));
							if (tableData.size() != 0) {

								// https://stackoverflow.com/questions/22340535/how-to-show-jrbeancollectiondatasource-data-with-help-of-table-component
								params.put(nameParam, new ListDataSource(tableData));
							} else {

								isNullParams = true;
							}
						} else {

							valueParam = field.get(objectParam).toString();
							if (valueParam != null) {

								params.put(nameParam, valueParam);
							} else {

								isNullParams = true;
							}
						}

						if (notNullParams != null)

							if (isMandatoryValue(nameParam) && isNullParams)

								isPrintReport = false;
					}

				}
				listMap.add(params);
			}
		} catch (Exception e) {
			throw new JasperGeneratorException("parameter setting error");
		}
	}

	private boolean isMandatoryValue(String inValue) {
		for (String item : notNullParams) {
			if (item.equals(inValue)) {

				return true;
			}

		}
		// this.notNullParams = notNullParams;
		return false;
	}

	private class ListDataSource implements JRDataSource {
		private List<Object> list;
		Field[] fields;
		private int idx;
		private int idxFild;

		public ListDataSource(List<Object> tableData) {
			this.list = tableData;
			this.idx = 0;
			fields = list.get(0).getClass().getFields();
		}

		public boolean next() throws JRException {
			boolean hasValues = idx < list.size();

			if (hasValues) {

				fields = list.get(idx).getClass().getDeclaredFields();
				idxFild = idx;
				idx++;
			}
			return hasValues;
		}

		public Object getFieldValue(JRField jrField) {
			String result = "";
			try {
				String a = jrField.getName();
				for (Field field : fields) {
					field.setAccessible(true);

					if (a.equals(field.getName())) {

						Object fieldValue;

						fieldValue = field.get(list.get(idxFild));

						if (fieldValue != null) {
							result = fieldValue.toString();
						}

					}

				}

			} catch (Exception e) {
				throw new JasperGeneratorException("error while accessing report fields");
			}
			
			if (result != null) {
				return result;
			} else {
				return "";
			}
		}

	}

}
