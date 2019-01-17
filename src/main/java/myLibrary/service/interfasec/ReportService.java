package myLibrary.service.interfasec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

public interface ReportService {
	
	File getPdfReport() throws  DocumentException, IOException;

}
