package com.jheidrich.rssreader;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EventDispatcher.EventObserver {

    ListView articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleList = (ListView) findViewById(R.id.articleList);

        // ToDo: Start job

        JobInfo rssJob = new JobInfo.Builder(1,new ComponentName(this,RSSService.class))
                .setPeriodic(10000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.schedule(rssJob);
        /*XMLProcessor processor = new XMLProcessor("https://www.pcwelt.de/rss/software.xml");
        processor.execute();*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventDispatcher.addObserver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventDispatcher.deleteObserver(this);
    }

    @Override
    public void feedUpdated(ArrayList<ArticleModel> posts) {
        ArrayAdapter<ArticleModel> arrayAdapter = new ArrayAdapter<ArticleModel>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                posts);
        articleList.setAdapter(arrayAdapter);
    }
}
