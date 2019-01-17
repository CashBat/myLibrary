package myLibrary.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.ejb.Stateless;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import myLibrary.service.interfasec.ReportService;

@Stateless
public class DefaultReportService implements ReportService {

	@Override
	public File getPdfReport() throws DocumentException, IOException {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		String outDir = "pdf";

		Path dirPath = Paths.get(outDir);
		if (!Files.exists(dirPath)) {
			Files.createDirectories(dirPath);		
		}

		File file = new File(outDir+"\\ITextTest.pdf");
		
		FileOutputStream fileStrem =new FileOutputStream(file);

		PdfWriter.getInstance(document,

				fileStrem);

		document.open();

		Anchor anchorTarget = new Anchor("First page of the document.");
		anchorTarget.setName("BackToTop");
		Paragraph paragraph1 = new Paragraph();

		paragraph1.setSpacingBefore(50);

		paragraph1.add(anchorTarget);
		document.add(paragraph1);

		document.add(new Paragraph("Some more text on the " +

				"first page with different color and font type.",

				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));
		document.close();

		return file;
	}

}
