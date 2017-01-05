package io.rocketfox.overwatchinfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.rocketfox.overwatchinfo.Objects.HeroData;
import io.rocketfox.overwatchinfo.Objects.HeroItem;
import io.rocketfox.overwatchinfo.R;

/**
 * Created by Lukas on 05.01.2017.
 */
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

        nameTXT.setText(data[position].name);
//TODO: Einfügen ImageView mit Asset


        return rowView;
    }

}
