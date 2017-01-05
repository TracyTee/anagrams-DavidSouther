/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();
    private List<String> dictionary = new ArrayList<String>();


    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            //
            //  Your code here
            dictionary.add(word);

            String temp =  sortLetters(word);
           if( !lettersToWord.containsKey(temp)){
               lettersToWord.put(temp,new ArrayList<String>());

           }
            lettersToWord.get(temp).add(word);
           }
        }

    public boolean isGoodWord(String word, String base) {
        return getAnagramsWithOneMoreLetter(base).contains(word);
        // Your code here
       // return (!word.equals(base) && isAnagram(word.toUpperCase(),base.toUpperCase()));

    }

    public List<String> getAnagrams(String targetWord) {
       // ArrayList<String> result = new ArrayList<String>();
        //
        // Your code here
        //go through master list and find anagrams of word
       /** for(String word:dictionary)
        {
            if(isAnagram(word,targetWord)){
                result.add(word);
            }
        }**/
        String sortedWord = sortLetters(targetWord);
        if(!lettersToWord.containsKey(sortedWord)){
            return new ArrayList<>();
        }
        return lettersToWord.get(sortedWord);
    }

    @VisibleForTesting
    static boolean isAnagram(String first, String second) {
        //
        // Your code here
        return sortLetters(first).equals(sortLetters(second));

    }

    @VisibleForTesting
    static String sortLetters(String input) {
        char[] chars = input.toCharArray();
        //
        // Your code here
        Arrays.sort(chars);
        String sortedWord = String.valueOf(chars);
        //
        return sortedWord;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        //
        // Your code here
        //going through the letters in the alphabet
       // char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(char i = 'a'; i<='z' ; i++)
        {
            String temp = sortLetters(word + i);
            result.addAll(getAnagrams(temp));
        }
      /**  for( char c: alphabet)
        {
            String temp = sortLetters(word + c);
            result.addAll(getAnagrams(temp));
        }**/

        return result;
    }

    public String pickGoodStarterWord() {
        //
        // Your code here
        //
        return "stop";
    }
}
