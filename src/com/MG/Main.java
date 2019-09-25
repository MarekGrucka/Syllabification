package com.MG;

import com.sun.javafx.scene.shape.PathUtils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {




        List<Path> filePaths = new ArrayList<>();

        try {
            Path initialPath = Paths.get(".");
            filePaths =
                        FileSearcher.searchRegularFilesStartsWith(initialPath, "", ".txt");
                        filePaths.stream().forEach(System.out::println);
        }
        catch (java.io.IOException e){
            System.out.println("No matching files found.");
        }


        try {
            List<String> txtFile = Files.readAllLines(filePaths.get(0), Charset.forName("Windows-1250"));

            List<String> result = Syllabizer.Syllabize(textFormater.format(txtFile));

            for (String word : result){
                System.out.print(word);
            }
            }
            //textFormater.format(txtFile);



        catch (java.io.IOException e){
            System.out.println(e.getClass() + e.getMessage());
        }





	// write your code here
    }
}
