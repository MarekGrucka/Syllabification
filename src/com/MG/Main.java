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
import org.apache.poi.extractor.POITextExtractor;
import static com.MG.Syllabizer.*;



public class Main {

    public static void main(String[] args) {

        List<Path> filePaths = new ArrayList<>();






        try {
            Path initialPath = Paths.get(".");
            List<Path>txtFilePaths =
                        FileSearcher.searchRegularFilesStartsWith(initialPath, "", ".txt");
                        //txtFilePaths.stream().forEach(System.out::println);
            List<Path>docFilePaths =
                    FileSearcher.searchRegularFilesStartsWith(initialPath, "", ".doc");
            List<Path>docxFilePaths =
                    FileSearcher.searchRegularFilesStartsWith(initialPath, "", ".docx");
            filePaths.addAll(txtFilePaths);
            filePaths.addAll(docFilePaths);
            filePaths.addAll(docxFilePaths);

            filePaths.stream().forEach(System.out::println);
        }
        catch (java.io.IOException e){
            System.out.println("No matching files found.");
        }


        try {
            List<String> txtFile = Files.readAllLines(filePaths.get(3), Charset.forName("Windows-1250"));

            List<String> result = syllabize(textFormater.format(txtFile));

            //for (String word : result){
            //    System.out.print(word);
           // }


            writeSyllabizedFile(filePaths.get(3), result);

            }
            //textFormater.format(txtFile);



        catch (java.io.IOException e){
            System.out.println(e.getClass() + e.getMessage());
        }


    }

    private static void writeSyllabizedFile(Path path, List<String> list) throws IOException {


        String[] pathElements = path.toString().split("\\.");

        Path outputPath = Paths.get(pathElements[0] + "Syllabized." + pathElements[1]);

        Files.write(outputPath, list, Charset.forName("Windows-1250"), StandardOpenOption.CREATE);

    try (BufferedWriter  writer = Files.newBufferedWriter(outputPath)) {

            for (String word : list) {
            writer.write(word);
         }
         }
    }
}
