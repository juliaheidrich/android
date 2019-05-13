package com.jheidrich.rssreader;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class ArticleModel {
    private String articleTitle;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public String toString() {
        return articleTitle;
    }
}
