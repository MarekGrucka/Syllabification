package com.mg;


import java.util.LinkedList;
import java.util.List;

class TextFormater {

    static List<String> format(List<String> txtFile) {

        List<String> listOfWords = new LinkedList<>();
        int wordIndex = 0;
        int numerOfApostrophes = 0;

        splitLinesOfTextIntoIndividualWordsToList(txtFile, listOfWords);

        List<String> tempListOfWords = new LinkedList<>(listOfWords);

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
                    removeNextSpace(wordIndex, listOfWords);
                    //wordIndex++;
                    break;
                case "z":
                    replaceNextspace(wordIndex, listOfWords);
                    wordIndex++;
                    break;
                case "Z":
                    replaceNextspace(wordIndex, listOfWords);
                    wordIndex++;
                    break;
                case "w":
                    replaceNextspace(wordIndex, listOfWords);
                    wordIndex++;
                    break;
                case "W":
                    replaceNextspace(wordIndex, listOfWords);
                    wordIndex++;
                    break;

                default:
                    wordIndex++;
            }

        }


        return listOfWords;

    }

    private static void splitLinesOfTextIntoIndividualWordsToList(List<String> txtFile, List<String> listOfWords) {
        for (String line : txtFile) {
            String[] splittedLine = line.split(" ");
            for (String word : splittedLine) {
                listOfWords.add(word);
                listOfWords.add(" ");
            }
        }
    }

    private static void removeNextSpace(int index, List<String> listOfWords) {
        if (index + 1 < listOfWords.size()) {
            listOfWords.remove(index + 1);
        }
    }

    private static void removeUnwantedSpace(int index, List<String> listOfWords) {
        if (index > 0) {
            listOfWords.remove(index - 1);
        }
    }

    private static void removeApostropheUnwantedSpace(int index, int numerOfApostrophes, List<String> listOfWords) {
        if (numerOfApostrophes % 2 == 0 && index > 0 && index + 1 < listOfWords.size()) {
            listOfWords.remove(index + 1);
        } else if (index < listOfWords.size()) {
            listOfWords.remove(index - 1);
        }
    }

    private static void replaceHyphen(int index, List<String> listOfWords) {
        listOfWords.remove(index);
        listOfWords.add(index, "–");
        }

    private static void replaceNextspace(int index, List<String> listOfWords) {
        listOfWords.remove(index + 1);
        listOfWords.add(index + 1, " ");

    }

}
