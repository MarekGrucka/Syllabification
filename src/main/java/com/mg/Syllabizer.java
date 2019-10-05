package com.mg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Syllabizer {

    static List<String> syllabize(final List<String> listOfWords) {


        List<String> sylabizedWords = new LinkedList<>();

        List<String> tempList = new LinkedList<>(listOfWords);


        for (String word : tempList) {

            StringBuilder sylable = new StringBuilder();

            int numberOfSylablesInWord = countNumberOfSylabes(word);
            int actualSylableIndex = 0;

            if (numberOfSylablesInWord <= 1) {
                sylabizedWords.add(word);
            } else {

                for (int i = 0; i < word.length(); i++) {

                    if (checkIfVowel(word.charAt(i))) {
                        actualSylableIndex++;
                        sylable.append(word.charAt(i));

                        if (i + 1 < word.length() && (word.charAt(i) == 'i' || word.charAt(i) == 'I') && checkIfVowel(word.charAt(i + 1))) {
                            i++;
                            sylable.append(word.charAt(i));
                        }

                        if (i + 1 < word.length() && (word.charAt(i + 1) == 'u' || word.charAt(i + 1) == 'U') && checkIfVowel(word.charAt(i))) {
                            i++;
                            sylable.append(word.charAt(i));
                        }

                        if (actualSylableIndex == numberOfSylablesInWord) {
                            if (i == word.length() - 1) {
                                sylabizedWords.add(sylable.toString());
                                sylable = new StringBuilder();
                            } else {
                                for (int o = i + 1; o < word.length(); o++) {
                                    sylable.append(word.charAt(o));
                                    sylabizedWords.add(sylable.toString());
                                    sylable = new StringBuilder();
                                    i++;
                                }
                            }
                        } else {
                            if (i + 2 < word.length() && !checkIfVowel(word.charAt(i + 1)) && !checkIfVowel(word.charAt(i + 2))) {
                                if ((word.charAt(i + 2) == 'z' && (word.charAt(i + 1) == 'c' || word.charAt(i + 1) == 's' || word.charAt(i + 1) == 'r' || word.charAt(i + 1) == 'd')) || word.charAt(i + 2) == 'h' && word.charAt(i + 1) == 'c' || word.charAt(i + 2) == 'ł'
                                        || word.charAt(i + 1) == 'ś' || word.charAt(i + 1) == 's' || word.charAt(i + 1) == 'b' || ((word.charAt(i + 2) == 'ż' || word.charAt(i + 2) == 'ź') && word.charAt(i + 1) == 'd')) {
                                    sylable.append('-');
                                    sylabizedWords.add(sylable.toString());
                                    sylable = new StringBuilder();

                                } else {
                                    if (i + 3 < word.length() && word.charAt(i + 1) == 'd' && word.charAt(i + 2) == 'r' && word.charAt(i + 3) == 'z') {
                                        sylable.append('-');
                                        sylabizedWords.add(sylable.toString());
                                        sylable = new StringBuilder();
                                    } else {
                                        sylable.append(word.charAt(i + 1));
                                        sylable.append('-');
                                        sylabizedWords.add(sylable.toString());
                                        sylable = new StringBuilder();
                                        i++;
                                    }
                                }

                            } else if (i == word.length() - 1) {
                                sylabizedWords.add(sylable.toString());
                                sylable = new StringBuilder();

                            } else {
                                sylable.append('-');
                                sylabizedWords.add(sylable.toString());
                                sylable = new StringBuilder();
                            }

                        }
                    }
                    // consonant or punctuation
                    else {
                        sylable.append(word.charAt(i));
                    }
                }
            }
        }
        return sylabizedWords;
    }
    private static final Character[] letters = { 'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U', 'ó', 'Ó', 'y', 'Y',
                                                 'ą', 'Ą', 'ę', 'Ę' };

    private static final List<Character> LETTERS = Arrays.asList(letters);


    private static boolean checkIfVowel(char letter) {

        return LETTERS.contains(letter);
    }

    private static int countNumberOfSylabes(String word) {
        int numberOfSylabes = 0;
        for (int i = 0; i < word.length(); i++) {
            if (checkIfVowel(word.charAt(i))) {
                if (!(i + 1 < word.length() && (word.charAt(i) == 'i' || word.charAt(i) == 'I') && checkIfVowel(word.charAt(i + 1)))) {
                    if (!(i + 1 < word.length() && (word.charAt(i + 1) == 'u' || word.charAt(i + 1) == 'U') && checkIfVowel(word.charAt(i)))) {
                        numberOfSylabes++;
                    }
                }
            }
        }
        return numberOfSylabes;
    }
}
