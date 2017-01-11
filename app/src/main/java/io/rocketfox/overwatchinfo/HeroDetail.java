package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponseHeroDetail;
import io.rocketfox.overwatchinfo.HTTPReq.HeroDetailReq;
import io.rocketfox.overwatchinfo.Objects.Hero;

public class HeroDetail extends AppCompatActivity implements AsyncResponseHeroDetail{
    LinearLayout lcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        Intent intent = getIntent();
        int heroID = intent.getIntExtra("HEROID", 1);

        lcontent = (LinearLayout) findViewById(R.id.content_linear);
        lcontent.setVisibility(View.GONE);
        new HeroDetailReq(this, heroID).execute();


        setTitle("Hero Details");
    }

    @Override
    public void onLoadingDone(Hero hero) {
        updateUI(hero);
    }

    private void updateUI(Hero hero) {
        lcontent.setVisibility(View.VISIBLE);
        setTitle("Hero Details: " + hero.name);
        TextView txtName = (TextView) findViewById(R.id.txtHeroNameDetail);
        txtName.setText(hero.name);

        TextView descriptionTag = (TextView) findViewById(R.id.txtDescriptionTagDetail);
        TextView informationsTag = (TextView) findViewById(R.id.txtInformationsTagDetail);

        Typeface overwatchfont = Typeface.createFromAsset(getAssets(), "fonts/big_noodle_titling.ttf");
        txtName.setTypeface(overwatchfont);
        descriptionTag.setTypeface(overwatchfont);
        informationsTag.setTypeface(overwatchfont);

        ImageView imgHeroAvatar = (ImageView) findViewById(R.id.imgHeroAvatarDetail);
        String heroName = hero.name.replace(" ", "").replace(".", "").replace(":", "").toLowerCase();
        imgHeroAvatar.setImageBitmap(getBitmapFromAsset(this, "icons/" + heroName + ".png"));

        TextView txtHealth = (TextView) findViewById(R.id.txtHeroHealthDetail);
        txtHealth.setText(String.valueOf(hero.health));


        TextView txtShield = (TextView) findViewById(R.id.txtHeroShieldDetail);
        ImageView imgShieldIcon = (ImageView) findViewById(R.id.imgShieldIcon);
        if(hero.shield == 0){
            if(hero.armour != 0) {
                imgShieldIcon.setImageResource(R.drawable.armor);
                txtShield.setText(String.valueOf(hero.armour));
            }
        }else{
            imgShieldIcon.setImageResource(R.drawable.shield);
            txtShield.setText(String.valueOf(hero.shield));
        }

        TextView txtDescription = (TextView) findViewById(R.id.txtHeroDescriptionDetail);
        txtDescription.setText(hero.description);

        TextView txtAge = (TextView) findViewById(R.id.txtHeroAgeDetail);
        txtAge.setText("Age: " + hero.age);

        TextView txtRealName = (TextView) findViewById(R.id.txtHeroRealNameDetail);
        if(hero.real_name != null)
             txtRealName.setText("Real name: " + hero.real_name);
        else
            txtRealName.setText("Real name: " + "unkown");

        TextView txtBaseOfOperations = (TextView) findViewById(R.id.txtHeroBaseDetail);
        if(hero.base_of_operations != null)
            txtBaseOfOperations.setText("Base of operations:" + hero.base_of_operations);
        else
            txtBaseOfOperations.setText("Base of operations:" + "unkown");

        TextView txtRoleText = (TextView) findViewById(R.id.txtRoleName);
        txtRoleText.setText(hero.role.name);
        txtRoleText.setTypeface(overwatchfont);

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
