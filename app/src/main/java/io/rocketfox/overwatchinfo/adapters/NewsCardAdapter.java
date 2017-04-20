package io.rocketfox.overwatchinfo.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rocketfox.overwatchinfo.HTTPReq.DownloadImageTask;
import io.rocketfox.overwatchinfo.MainActivity;
import io.rocketfox.overwatchinfo.NewsFragment;
import io.rocketfox.overwatchinfo.Objects.PatchNote;
import io.rocketfox.overwatchinfo.R;
import nl.matshofman.saxrssreader.RssItem;

/**
 * Created by imp_lku on 04.01.2017.
 */
public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Button btnMore;
        public ImageView mImageViewHeader;
        public TextView mTextViewTitle;
        public ViewHolder(View v) {
            super(v);
            mImageViewHeader = (ImageView) v.findViewById(R.id.imgNews);
            mTextViewTitle = (TextView) v.findViewById(R.id.txtNewsTitle);
            btnMore = (Button) v.findViewById(R.id.btnMore);
        }
    }

    private ArrayList<RssItem> items;
    private Context context;

    @Override
    public NewsCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            Typeface overwatchfont = Typeface.createFromAsset(context.getAssets(), "fonts/big_noodle_titling.ttf");
            Typeface overwatchfontItalic = Typeface.createFromAsset(context.getAssets(), "fonts/big_noodle_titling_oblique.ttf");
            holder.mTextViewTitle.setTypeface(overwatchfont);
            holder.mTextViewTitle.setText(items.get(position).getTitle());
            String imgRegex = "<[iI][mM][gG][^>]+[sS][rR][cC]\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";

            holder.btnMore.setTypeface(overwatchfontItalic);
            holder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(position).getLink()));
                    context.startActivity(intent);
                }
            });

            Pattern p = Pattern.compile(imgRegex);
            Matcher m = p.matcher(items.get(position).getDescription());
            if (m.find()) {
                String imgSrc = m.group(1);
                try {
                    Glide.with(context).load(imgSrc).into(holder.mImageViewHeader);
                    //holder.mImageViewHeader.setImageBitmap(new DownloadImageTask().execute(imgSrc).get());
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }catch(Exception ex){
            FirebaseCrash.report(ex);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public NewsCardAdapter(ArrayList<RssItem> items, Context context) {
        this.items = items;
        this.context = context;
    }
}
