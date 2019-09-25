package com.MG;

import java.util.LinkedList;
import java.util.List;

public class Syllabizer {

    public static List<String> Syllabize (List<String> listOfWords) {

        List<String> tempList = new LinkedList<>();
        List<String> sylabizedWords = new LinkedList<>();

        for (String word : listOfWords) {
            tempList.add(word);
        }

        for (String word : tempList) {

            StringBuilder sylabe = new StringBuilder();

            int numberOfSylabes = 0;
            int vowel = 0;

            for (int i = 0; i < word.length(); i++) {
                if (checkIfVowel(word.charAt(i))) {
                    if ((word.charAt(i) == 'i' || word.charAt(i) == 'I') && i + 1 < word.length() && checkIfVowel(word.charAt(i+1))){

                    }
                    else {
                        numberOfSylabes++;
                    }
                }
            }
                //System.out.println(numberOfSylabes);

                if (numberOfSylabes < 2) {
                    sylabizedWords.add(word);

                } else {

                    for (int i = 0; i < word.length(); i++) {

                        if (checkIfVowel(word.charAt(i))) {
                            vowel++;
                            sylabe.append(word.charAt(i));

                            if ((word.charAt(i) == 'i' || word.charAt(i) == 'I') && i + 1 < word.length() && checkIfVowel(word.charAt(i+1))) {
                                i++;
                                sylabe.append(word.charAt(i));
                            }

                            if (vowel == numberOfSylabes) {
                                    if (i == word.length() - 1) {
                                    sylabizedWords.add(sylabe.toString());
                                    sylabe = new StringBuilder();
                                }

                                else {
                                    for (int o = i+1; o < word.length(); o++) {
                                        sylabe.append(word.charAt(o));
                                        sylabizedWords.add(sylabe.toString());
                                        sylabe = new StringBuilder();
                                        i++;
                                    }
                                }


                            } else {
                                if (i + 2 < word.length() && !checkIfVowel(word.charAt(i + 1)) && !checkIfVowel(word.charAt(i + 2))) {
                                    if ((word.charAt(i + 2) == 'z' && (word.charAt(i + 1) == 'c' || word.charAt(i + 1) == 's' || word.charAt(i + 1) == 'r' || word.charAt(i+1) == 'd')) || word.charAt(i + 2) == 'h' && word.charAt(i + 1) == 'c' || word.charAt(i+2) == 'ł'
                                    || word.charAt(i+1) == 'ś' || word.charAt(i+1) == 's' || ((word.charAt(i+2) == 'ż' || word.charAt(i+2) == 'ź') && word.charAt(i+1) == 'd')) {
                                        sylabe.append('-');
                                        sylabizedWords.add(sylabe.toString());
                                        sylabe = new StringBuilder();

                                    } else {
                                        if (i+3 < word.length() && word.charAt(i+1) == 'd' && word.charAt(i+2) == 'r' && word.charAt(i+3) == 'z'){
                                            sylabe.append('-');
                                            sylabizedWords.add(sylabe.toString());
                                            sylabe = new StringBuilder();
                                        }
                                        else {
                                            sylabe.append(word.charAt(i + 1));
                                            sylabe.append('-');
                                            sylabizedWords.add(sylabe.toString());
                                            sylabe = new StringBuilder();
                                            i++;
                                        }
                                    }

                                } else if (i == word.length() - 1) {
                                    sylabizedWords.add(sylabe.toString());
                                    sylabe = new StringBuilder();

                                } else {
                                    sylabe.append('-');
                                    sylabizedWords.add(sylabe.toString());
                                    sylabe = new StringBuilder();
                                }

                            }
                        }
                            // consonant or punctuation
                            else {
                                sylabe.append(word.charAt(i));
                            }
                        }
                }
        }
            return sylabizedWords;
    }

    private static boolean checkIfVowel(char letter){
        return letter == 'a' || letter == 'A' || letter == 'e' || letter == 'E' || letter == 'i' || letter == 'I' || letter == 'o' || letter == 'O' || letter == 'u' || letter == 'U' || letter == 'ó' || letter == 'Ó' || letter == 'y' || letter == 'Y' || letter == 'ą' || letter == 'Ą' || letter == 'ę' || letter == 'Ę';

    }

}


/// WIELKIE LITERY DODAC