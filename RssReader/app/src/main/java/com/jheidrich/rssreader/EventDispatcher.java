package com.jheidrich.rssreader;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class EventDispatcher {

    public interface EventObserver{
        void feedUpdated(ArrayList<ArticleModel> posts);
    }

    private static List<EventObserver> observers = new ArrayList<EventObserver>();
    public static void addObserver(EventObserver observer){
        observers.add(observer);
    }

    public static void deleteObserver(EventObserver observer){
        observers.remove(observer);
    }

    public static void dispatchEvent(ArrayList<ArticleModel> posts){
        for(EventObserver observer : observers){
            observer.feedUpdated(posts);
        }
    }
}
