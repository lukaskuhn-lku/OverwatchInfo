package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;

import io.paperdb.Paper;
import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponseHeroDetail;
import io.rocketfox.overwatchinfo.HTTPReq.HeroDetailReq;
import io.rocketfox.overwatchinfo.Objects.Hero;

public class HeroDetailOverview extends Fragment implements AsyncResponseHeroDetail {

    LinearLayout lcontent1;

    private int heroID;

    ProgressBar loadingBar;

    public HeroDetailOverview() {}

    public static HeroDetailOverview newInstance(int heroID) {
        HeroDetailOverview fragment = new HeroDetailOverview();
        Bundle args = new Bundle();
        args.putInt("heroID", heroID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Hero Detail");

        if(getArguments() != null){
            Bundle args = getArguments();
            heroID = args.getInt("heroID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hero_detail_overview, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);

        new HeroDetailReq(this, heroID).execute();

        getActivity().setTitle("Hero Overview");

        lcontent1 = (LinearLayout) getView().findViewById(R.id.content_linearDetail);
        lcontent1.setVisibility(View.GONE);

        loadingBar = (ProgressBar) getView().findViewById(R.id.loadingBarDetail);
        loadingBar.setVisibility(View.VISIBLE);

        Hero hero = Paper.book().read("hero_" + heroID);
        if(hero != null){
            updateUI(hero);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void updateUI(Hero hero) {
        View v = getView();
        loadingBar.setVisibility(View.GONE);
        lcontent1.setVisibility(View.VISIBLE);
        TextView txtName = (TextView) v.findViewById(R.id.txtHeroNameDetail);
        txtName.setText(hero.name);

        TextView descriptionTag = (TextView) v.findViewById(R.id.txtDescriptionTagDetail);
        TextView informationsTag = (TextView) v.findViewById(R.id.txtInformationsTagDetail);


        Typeface overwatchfont = Typeface.createFromAsset(getContext().getAssets(), "fonts/big_noodle_titling.ttf");
        txtName.setTypeface(overwatchfont);
        descriptionTag.setTypeface(overwatchfont);
        informationsTag.setTypeface(overwatchfont);

        ImageView imgHeroAvatar = (ImageView) v.findViewById(R.id.imgHeroAvatarDetail);
        String heroName = hero.name.replace(" ", "").replace(".", "").replace(":", "").toLowerCase();
        imgHeroAvatar.setImageBitmap(getBitmapFromAsset(this.getContext(), "icons/" + heroName + ".png"));

        TextView txtHealth = (TextView) v.findViewById(R.id.txtHeroHealthDetail);
        txtHealth.setText(String.valueOf(hero.health));


        TextView txtShield = (TextView) v.findViewById(R.id.txtHeroShieldDetail);
        ImageView imgShieldIcon = (ImageView) v.findViewById(R.id.imgShieldIcon);
        if(hero.shield == 0){
            if(hero.armour != 0) {
                imgShieldIcon.setImageResource(R.drawable.armor);
                txtShield.setText(String.valueOf(hero.armour));
            }
        }else{
            imgShieldIcon.setImageResource(R.drawable.shield);
            txtShield.setText(String.valueOf(hero.shield));
        }

        TextView txtDescription = (TextView) v.findViewById(R.id.txtHeroDescriptionDetail);
        txtDescription.setText(hero.description);

        TextView txtAge = (TextView) v.findViewById(R.id.txtHeroAgeDetail);
        txtAge.setText("Age: " + hero.age);

        TextView txtRealName = (TextView) v.findViewById(R.id.txtHeroRealNameDetail);
        if(hero.real_name != null)
            txtRealName.setText("Real name: " + hero.real_name);
        else
            txtRealName.setText("Real name: " + "unkown");

        TextView txtBaseOfOperations = (TextView) v.findViewById(R.id.txtHeroBaseDetail);
        if(hero.base_of_operations != null)
            txtBaseOfOperations.setText("Base of operations: " + hero.base_of_operations);
        else
            txtBaseOfOperations.setText("Base of operations: " + "Unkown");

        TextView txtRoleText = (TextView) v.findViewById(R.id.txtRoleName);
        txtRoleText.setText(hero.role.name);
        txtRoleText.setTypeface(overwatchfont);

        Button btnAbilities = (Button) v.findViewById(R.id.btnAbilitiesDetail);
        btnAbilities.setTypeface(overwatchfont);

        btnAbilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_layout_detail, HeroDetailAbilities.newInstance(heroID)).addToBackStack("OVERVIEW").commit();
            }
        });
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

    @Override
    public void onLoadingDone(Hero hero) {
        try {
            updateUI(hero);
            Paper.book().write("hero_" + hero.id, hero);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
