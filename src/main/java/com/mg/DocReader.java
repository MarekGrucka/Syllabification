package com.mg;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.nio.file.Path;

public class DocReader {

	public static List<String> readDocFile(Path path) {

        List<String> readFile = new ArrayList<>();
        String fileName = path.toString();

		try {

			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			HWPFDocument doc = new HWPFDocument(fis);

			WordExtractor we = new WordExtractor(doc);

			String[] paragraphs = we.getParagraphText();
			

			for (String para : paragraphs) {
			    readFile.add(para);
				}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readFile;

	}

	public static List<String> readDocxFile(Path path) {

		List<String> readFile = new ArrayList<>();
		String fileName = path.toString();

		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			XWPFDocument document = new XWPFDocument(fis);

			List<XWPFParagraph> paragraphs = document.getParagraphs();
			

			for (XWPFParagraph para : paragraphs) {
				readFile.add(para.getText());
				}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readFile;
	}


}
