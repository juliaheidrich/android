package com.jheidrich.rssreader;

import java.util.ArrayList;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public interface PostParserDelegateInterface {

    public void xmlFeedParsed(ArrayList<ArticleModel> posts);
}
