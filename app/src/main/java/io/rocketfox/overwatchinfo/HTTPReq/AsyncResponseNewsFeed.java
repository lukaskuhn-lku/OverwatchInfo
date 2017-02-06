package io.rocketfox.overwatchinfo.HTTPReq;

import java.util.ArrayList;

import io.rocketfox.overwatchinfo.Objects.Hero;
import nl.matshofman.saxrssreader.RssItem;

/**
 * Created by Lukas on 20.01.2017.
 */

public interface AsyncResponseNewsFeed {
    void onLoadingDone(ArrayList<RssItem> feed);
}
