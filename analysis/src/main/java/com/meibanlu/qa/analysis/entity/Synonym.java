package com.meibanlu.qa.analysis.entity;

/**
 * 同义词
 */
public class Synonym {
    /**
     * 主词
     */
    private String mainWord;
    /**
     * 同义词
     */
    private String synonymWord;

    public Synonym() {
    }

    public Synonym(String mainWord, String synonymWord) {
        this.mainWord = mainWord;
        this.synonymWord = synonymWord;
    }

    public String getMainWord() {
        return mainWord;
    }

    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;
    }

    public String getSynonymWord() {
        return synonymWord;
    }

    public void setSynonymWord(String synonymWord) {
        this.synonymWord = synonymWord;
    }
}
