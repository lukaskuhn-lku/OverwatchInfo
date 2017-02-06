package io.rocketfox.overwatchinfo.HTTPReq;

import android.os.AsyncTask;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

public class RSSFeedReq extends AsyncTask<Void, Void, ArrayList<RssItem>>{
    AsyncResponseNewsFeed delegate = null;

    public RSSFeedReq(AsyncResponseNewsFeed delegate) {
        this.delegate = delegate;
    }

    @Override
    protected ArrayList<RssItem> doInBackground(Void... voids) {
        try {
            URL url = new URL("https://medium.com/feed/tag/overwatch");
            RssFeed feed = RssReader.read(url);

            ArrayList<RssItem> rssItems = feed.getRssItems();
            return rssItems;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<RssItem> feed) {
            delegate.onLoadingDone(feed);
    }

}
