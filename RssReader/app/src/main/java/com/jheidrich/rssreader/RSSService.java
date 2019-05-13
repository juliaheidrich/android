package com.jheidrich.rssreader;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class RSSService extends JobService {

    private JobParameters params;

    public JobParameters getParams() {
        return params;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        this.params = params;
        XMLProcessor processor = new XMLProcessor("https://www.pcwelt.de/rss/software.xml");
        processor.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }




    public class XMLProcessor extends AsyncTask<String, Void, String> {

        private String rssURL;
        //private PostParserDelegateInterface delegate;
        private ArrayList<ArticleModel> posts;
        private StringBuilder buffer;

        public XMLProcessor(String rssURL) {
            this.rssURL = rssURL;
            //this.delegate = delegate;
            posts = new ArrayList<ArticleModel>();
        }

        @Override
        protected String doInBackground(String... strings) {

            buffer = new StringBuilder();
            try {
                URL url = new URL(rssURL);
                //HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                int httpsResponse = httpsURLConnection.getResponseCode();
                if(httpsResponse != 200){
                    throw new Exception( "HTTPS Error: " + httpsResponse);
                }

                InputStream input = httpsURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);

                int charactersRead;
                char[] tmpChars = new char[400];

                while(true){
                    charactersRead = reader.read(tmpChars);
                    if(charactersRead <= 0 ) {
                        // reached end of document
                        break;
                    }
                    buffer.append(String.copyValueOf(tmpChars,0, charactersRead));
                }

                return buffer.toString();

            } catch (Exception e) {
                Log.e("XMLProcessor", e.getStackTrace().toString());
                Log.e("XMLProcessor", e.getMessage());
            }

            // ToDo: download
            // buffer download
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            // things to do when download is ready
            super.onPostExecute(s);
            parse();
        }

        protected void parse() {
            // ToDO: XML Verarbeiten

            String rawXML = buffer.toString();

            ArticleModel aArticle = null;
            boolean isProcessingItem = false;
            String innerValue = "";

            try{

                XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                parser.setInput(new StringReader(rawXML));

                int event = parser.getEventType();
                while(event != XmlPullParser.END_DOCUMENT){
                    String tag = parser.getName();
                    switch(event){
                        case XmlPullParser.START_TAG:
                            if(tag.equalsIgnoreCase("item")){
                                Log.d("XMLProcessor","Neuer Post!");
                                isProcessingItem = true;
                                aArticle = new ArticleModel();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            innerValue = parser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(isProcessingItem){
                                if(tag.equalsIgnoreCase("item")){
                                    posts.add(aArticle);
                                    isProcessingItem = false;
                                }else if(tag.equalsIgnoreCase("title")){
                                    aArticle.setArticleTitle(innerValue);
                                }
                            }

                            break;
                    }

                    event = parser.next();

                }

                EventDispatcher.dispatchEvent(posts);
                jobFinished(getParams(), true);

            }catch(Exception e){
                Log.e("XMLProcessor", e.getStackTrace().toString());
            }


        }
    }

}
