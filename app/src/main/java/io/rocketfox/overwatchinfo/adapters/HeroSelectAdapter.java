package io.rocketfox.overwatchinfo.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.rocketfox.overwatchinfo.Objects.HeroData;
import io.rocketfox.overwatchinfo.Objects.HeroItem;
import io.rocketfox.overwatchinfo.R;

public class HeroSelectAdapter extends ArrayAdapter<HeroItem> {

    private final Context context;
    private final HeroItem[] data;

    public HeroSelectAdapter(Context context, HeroItem[] data) {
        super(context, -1 , data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.heroselect_item, parent, false);

        TextView nameTXT = (TextView) rowView.findViewById(R.id.heroselect_name);
        ImageView avatar = (ImageView) rowView.findViewById(R.id.heroselect_avatar);

        Typeface overwatchfont = Typeface.createFromAsset(getContext().getAssets(), "fonts/big_noodle_titling.ttf");

        nameTXT.setTypeface(overwatchfont);
        nameTXT.setText(data[position].name);

        String heroName = data[position].name.replace(" ", "").replace(".", "").replace(":", "").toLowerCase();

        avatar.setImageBitmap(getBitmapFromAsset(rowView.getContext(), "icons/" + heroName + ".png"));

        return rowView;
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
                istr = assetManager.open("heronotfound.png");
                bitmap = BitmapFactory.decodeStream(istr);
            }catch(Exception ex){
                ex.printStackTrace();
                return null;
            }
        }

        return bitmap;
    }

}
