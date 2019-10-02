package com.mg;


import java.util.LinkedList;
import java.util.List;

public class TextFormater {

    public static List<String> format(List<String> txtFile) {

     List<String> listOfWords = new LinkedList<>();
     List<String> tempListOfWords = new LinkedList<>();
     int wordIndex = 0;
     int numerOfApostrophes = 0;

        for (String line : txtFile){
            String[] splittedLine = line.split(" ");
            for (String word : splittedLine){
                listOfWords.add(word);
                listOfWords.add(" ");
            }
        }

        for (String word : listOfWords){
            tempListOfWords.add(word);
        }


    for (String interpunction : tempListOfWords) {


        switch (interpunction) {
            case ",":
                removeUnwantedSpace(wordIndex, listOfWords);
                break;
            case ".":
                removeUnwantedSpace(wordIndex, listOfWords);
                break;
            case "!":
                removeUnwantedSpace(wordIndex, listOfWords);
                break;
            case "?":
                removeUnwantedSpace(wordIndex, listOfWords);
                break;
            case "\"":
                removeApostropheUnwantedSpace(wordIndex, numerOfApostrophes, listOfWords);
                break;
            case "-":
                replaceHyphen(wordIndex, listOfWords);
                wordIndex++;
                break;
            case "z":
                replaceNextspace(wordIndex, listOfWords);
                wordIndex++;
                break;
            case "Z":
                replaceNextspace(wordIndex, listOfWords);
                wordIndex++;
                break;
            case  "w":
                replaceNextspace(wordIndex, listOfWords);
                wordIndex++;
                break;
            case  "W":
                replaceNextspace(wordIndex, listOfWords);
                wordIndex++;
                break;
            default:
                wordIndex++;
        }

    }


    return listOfWords;

    }

    private static void removeUnwantedSpace(int index, List listOfWords){
        if(index>0){
            listOfWords.remove(index-1);
            }
    }
    private static void removeApostropheUnwantedSpace(int index, int numerOfApostrophes, List listOfWords){
        if(numerOfApostrophes%2==0 && index>0 && index+1<listOfWords.size()){
            listOfWords.remove(index+1);
        }
        else if(index<listOfWords.size()) {
            listOfWords.remove(index-1);
        }
    }

    private static void replaceHyphen (int index, List listOfWords){
        listOfWords.remove(index);
        listOfWords.add(index, "–");
        if(index+1<listOfWords.size()){
            listOfWords.remove(index+1);
        }

    }

    private static void replaceNextspace (int index, List listOfWords){
        listOfWords.remove(index+1);
        listOfWords.add(index+1, " ");

    }

}
