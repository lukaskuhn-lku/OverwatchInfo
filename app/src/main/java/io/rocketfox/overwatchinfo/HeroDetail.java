package io.rocketfox.overwatchinfo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.paperdb.Paper;

public class HeroDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        Intent intent = getIntent();
        int heroID = intent.getIntExtra("HEROID", 1);
        setTitle("Hero Details");

        Paper.init(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = HeroDetailOverview.newInstance(heroID);
        fragmentManager.beginTransaction().replace(R.id.content_layout_detail, fragment).commit();
    }


}
