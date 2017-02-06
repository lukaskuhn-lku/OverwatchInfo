package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.ArrayList;

import io.paperdb.Paper;
import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponseNewsFeed;
import io.rocketfox.overwatchinfo.HTTPReq.RSSFeedReq;
import io.rocketfox.overwatchinfo.adapters.NewsCardAdapter;
import io.rocketfox.overwatchinfo.adapters.PatchNoteAdapter;
import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

public class NewsFragment extends Fragment implements AsyncResponseNewsFeed{

    public NewsFragment() {
    }


    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    public void onButtonPressed(Uri uri) { }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    RecyclerView recyclerView;
    ProgressBar loadingBar;
    @Override
    public void onViewCreated(View v, Bundle savedInstance){
        super.onViewCreated(v, savedInstance);
       try {
           loadingBar = (ProgressBar) v.findViewById(R.id.loadingBarNews);
           loadingBar.setVisibility(View.VISIBLE);
           new RSSFeedReq(this).execute();
           recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_news);
           recyclerView.setHasFixedSize(true);
           RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
           recyclerView.setLayoutManager(layoutManager);
       }catch(Exception ex) {
           ex.printStackTrace();
       }
    }

    private ArrayList<RssItem> feed;
    @Override
    public void onLoadingDone(ArrayList<RssItem> feed) {
        this.feed = feed;
        loadingBar.setVisibility(View.GONE);
        NewsCardAdapter adapter = new NewsCardAdapter(feed, getContext());
        recyclerView.setAdapter(adapter);
    }


}
