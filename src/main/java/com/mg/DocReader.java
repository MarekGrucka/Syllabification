package com.mg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.nio.file.Path;

class DocReader {

    private static List<String> readDocFile(Path path) {

        List<String> readFile = new ArrayList<>();
        String fileName = path.toString();

        try {

            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            HWPFDocument doc = new HWPFDocument(fis);

            WordExtractor we = new WordExtractor(doc);

            String[] paragraphs = we.getParagraphText();


            readFile.addAll(Arrays.asList(paragraphs));

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readFile;

    }

    private static List<String> readDocxFile(Path path) {

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


    static List<String> getDocPath(Path path) throws IOException {
        String fileName = path.getFileName().toString();
        if (fileName.endsWith(".txt")) return Files.readAllLines(path, Charset.forName("Windows-1250"));
        if (fileName.endsWith(".doc")) return readDocFile(path);
        if (fileName.endsWith(".docx")) return readDocxFile(path);
        else return new ArrayList<>();

    }

}
