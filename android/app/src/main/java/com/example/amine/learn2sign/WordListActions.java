package com.example.amine.learn2sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordListActions {
    private Map<String,Integer> wordCount;
    private boolean minimumNumberOfVideosCompleted;
    private String [] wordList = {"And","About","Can","Cat","Cop","Cost","Day","Deaf","Decide","Father","Find","Go Out",
            "Gold","Goodnight","Hearing","Here","Hospital","Hurt","If","Large","Hello","Help","Sorry","After","Tiger"};;
    public void WordListActions(){
        wordCount = new HashMap<String,Integer>();
        minimumNumberOfVideosCompleted = false;
        


    }

    public void addWord(String word){
        if(wordCount.containsKey(word)){
            wordCount.put(word,wordCount.get(word) + 1);
        }
        else{
            wordCount.put(word,1);
        }
    }

    public void setMinimumNumberOfVideosCompletedToTrue(){
        Iterator it = wordCount.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            if((int)pair.getValue() < 3)
                return;
        }
        if(wordCount.size() >= 25)
            this.minimumNumberOfVideosCompleted = true;

    }

    public boolean returnMinimumVideos(){
        return minimumNumberOfVideosCompleted;
    }

    public String randomWord(){
        return this.wordList[new Random().nextInt(wordList.length)];
    }

}
