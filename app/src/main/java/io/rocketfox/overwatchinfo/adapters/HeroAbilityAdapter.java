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

import io.rocketfox.overwatchinfo.Objects.Hero;
import io.rocketfox.overwatchinfo.Objects.HeroData;
import io.rocketfox.overwatchinfo.Objects.HeroExtra.Ability;
import io.rocketfox.overwatchinfo.Objects.HeroItem;
import io.rocketfox.overwatchinfo.R;

public class HeroAbilityAdapter extends ArrayAdapter<Ability> {

    private final Context context;
    private final Ability[] abilities;

    public HeroAbilityAdapter(Context context, Ability[] abilities) {
        super(context, -1 , abilities);
        this.context = context;
        this.abilities = abilities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ability_item, parent, false);

        Typeface overwatchfont = Typeface.createFromAsset(getContext().getAssets(), "fonts/big_noodle_titling.ttf");

        TextView txtHeader = (TextView) rowView.findViewById(R.id.txtAbilityHeader);
        txtHeader.setTypeface(overwatchfont);

        txtHeader.setText(abilities[position].name);

        TextView txtDesc = (TextView) rowView.findViewById(R.id.txtAbilityDescription);
        txtDesc.setTypeface(overwatchfont);

        txtDesc.setText(abilities[position].description);

        return rowView;
    }
}