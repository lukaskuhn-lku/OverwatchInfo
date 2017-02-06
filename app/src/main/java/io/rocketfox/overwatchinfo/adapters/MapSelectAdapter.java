package io.rocketfox.overwatchinfo.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rocketfox.overwatchinfo.HTTPReq.DownloadImageTask;
import io.rocketfox.overwatchinfo.MainActivity;
import io.rocketfox.overwatchinfo.NewsFragment;
import io.rocketfox.overwatchinfo.Objects.Map;
import io.rocketfox.overwatchinfo.Objects.PatchNote;
import io.rocketfox.overwatchinfo.R;
import nl.matshofman.saxrssreader.RssItem;

/**
 * Created by imp_lku on 04.01.2017.
 */
public class MapSelectAdapter extends RecyclerView.Adapter<MapSelectAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageViewHeader;
        public TextView mTextViewTitle;
        public Button btnInfo;
        public ViewHolder(View v) {
            super(v);
            mImageViewHeader = (ImageView) v.findViewById(R.id.imgMapHeader);
            mTextViewTitle = (TextView) v.findViewById(R.id.txtMapName);
            btnInfo = (Button) v.findViewById(R.id.btnWiki);
        }
    }

    private ArrayList<Map> maps;
    private Context context;

    @Override
    public MapSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_map_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    String link;
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Typeface overwatchfont = Typeface.createFromAsset(context.getAssets(), "fonts/big_noodle_titling.ttf");
        Typeface overwatchfontItalic = Typeface.createFromAsset(context.getAssets(), "fonts/big_noodle_titling_oblique.ttf");

        String name = maps.get(position).name;

        holder.mTextViewTitle.setTypeface(overwatchfont);
        holder.mTextViewTitle.setText(name);

        name = name.replace(" ","_");
        link = name;
        link = "http://overwatch.gamepedia.com/" + name;
        name = name.replace(":", "");

        holder.btnInfo.setTypeface(overwatchfontItalic);
        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                context.startActivity(intent);
            }
        });

        String url = "file:///android_asset/maps/" + name.toLowerCase() + ".jpg";
        Glide.with(context)
                .load(Uri.parse(url))
                .into(holder.mImageViewHeader);
    }

    @Override
    public int getItemCount() {
        return maps.size();
    }

    public MapSelectAdapter(ArrayList<Map> maps, Context context) {
        this.maps = maps;
        this.context = context;
    }

    private static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                istr = assetManager.open("missingImage.png");
                bitmap = BitmapFactory.decodeStream(istr);
            }catch(Exception ex){
                ex.printStackTrace();
                return null;
            }
        }

        return bitmap;
    }

}
