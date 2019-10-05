package com.mg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static com.mg.DocReader.getDocPath;
import static com.mg.Syllabizer.syllabize;

public class Main {

    public static void main(String[] args) {

        List<Path> filePaths = new ArrayList<>();

        try {
            Path paths = Path.of(System.getProperty("app.resource"));
            filePaths.addAll(FileSearcher.searchRegularFilesStartsWith(paths, "", ".txt"));
            filePaths.addAll(FileSearcher.searchRegularFilesStartsWith(paths, "", ".doc"));
            filePaths.addAll(FileSearcher.searchRegularFilesStartsWith(paths, "", ".docx"));
        } catch (java.io.IOException e) {
            System.out.println("No matching files found.");
        }

        try {
            sylabizeAndSaveFile(filePaths);
        } catch (java.io.IOException e) {
            System.out.println(e.getClass() + e.getMessage());
        }
    }

    private static void writeSyllabizedFile(Path path, List<String> list) throws IOException {
        String[] pathElements = path.toString().split("\\.");
        Path outputPath = Paths.get(pathElements[0] + "Syllabized." + "txt");
        Files.write(outputPath, list, Charset.forName("Windows-1250"), StandardOpenOption.CREATE);
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            for (String word : list) {
                writer.write(word);
            }
        }
    }


    private static void sylabizeAndSaveFile(List<Path> paths) throws IOException {
        for (Path path : paths) {
            List<String> docPath = getDocPath(path);
            List<String> result = syllabize(TextFormater.format(docPath));
            writeSyllabizedFile(path, result);
        }
    }

}
