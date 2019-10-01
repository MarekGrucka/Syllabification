package com.MG;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.extractor.POITextExtractor;
import static com.MG.Syllabizer.*;

public class Main {

    public static void main(String[] args) {

        List<Path> txtFilePaths = new ArrayList<>();
        List<Path> docFilePaths = new ArrayList<>();
        List<Path> docxFilePaths = new ArrayList<>();

        try {
            Path initialPath = Paths.get(".");

            txtFilePaths = FileSearcher.searchRegularFilesStartsWith(initialPath, "", ".txt");
            docFilePaths = FileSearcher.searchRegularFilesStartsWith(initialPath, "", ".doc");
            docxFilePaths = FileSearcher.searchRegularFilesStartsWith(initialPath, "", ".docx");

        }
        catch (java.io.IOException e){System.out.println("No matching files found.");}


        try {sylabizeAllTypeFiles(txtFilePaths,docFilePaths, docxFilePaths); }
        catch (java.io.IOException e){System.out.println(e.getClass() + e.getMessage());}

    }

    private static void writeSyllabizedFile(Path path, List<String> list) throws IOException {


        String[] pathElements = path.toString().split("\\.");
        Path outputPath = Paths.get(pathElements[0] + "Syllabized." + "txt");
        Files.write(outputPath, list, Charset.forName("Windows-1250"), StandardOpenOption.CREATE);
         try (BufferedWriter  writer = Files.newBufferedWriter(outputPath)) {

            for (String word : list) {
            writer.write(word);
            }
         }
    }
    private static void sylabizeAllTypeFiles(List<Path> txtFilePaths,   List<Path> docFilePaths,   List<Path> docxFilePaths) throws java.io.IOException {

        for (Path txtfilepath : txtFilePaths) {
            List<String> txtFile = Files.readAllLines(txtfilepath, Charset.forName("Windows-1250"));
            List<String> result = syllabize(textFormater.format(txtFile));
            writeSyllabizedFile(txtfilepath, result);
        }

        for (Path docfilepath : docFilePaths) {
            List<String> docFile = DocReader.readDocFile(docfilepath);
            List<String> result = syllabize(textFormater.format(docFile));
            writeSyllabizedFile(docfilepath, result);
        }
        for (Path docxfilepath : docxFilePaths) {
            List<String> docxFile = DocReader.readDocFile(docxfilepath);
            List<String> result = syllabize(textFormater.format(docxFile));
            writeSyllabizedFile(docxfilepath, result);
        }
    }

}
