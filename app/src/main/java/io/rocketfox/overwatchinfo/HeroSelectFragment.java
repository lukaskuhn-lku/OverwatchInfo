package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponseHeroData;
import io.rocketfox.overwatchinfo.HTTPReq.HeroReq;
import io.rocketfox.overwatchinfo.Objects.HeroData;
import io.rocketfox.overwatchinfo.Objects.HeroItem;
import io.rocketfox.overwatchinfo.adapters.HeroSelectAdapter;


public class HeroSelectFragment extends Fragment implements AsyncResponseHeroData, AdapterView.OnItemClickListener {

    private HeroData heroData;
    private Context context;
    private ListView heroSelect;

    public HeroSelectFragment() {
    }

    public static HeroSelectFragment newInstance(String param1, String param2) {
        HeroSelectFragment fragment = new HeroSelectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        heroSelect = (ListView) getView().findViewById(R.id.list_view_heroes);
        try {
           new HeroReq(this).execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hero_select, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onLoadingDone(HeroData data) {
        heroData = data;
        updateUI(heroData);
    }

    private void updateUI(HeroData heroData) {
        View v = getView();

        ArrayList<HeroItem> items = heroData.getData();
        HeroItem[] array = items.toArray(new HeroItem[items.size()]);

        heroSelect.setAdapter(new HeroSelectAdapter(getContext(),array));
        heroSelect.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HeroItem heroItem = (HeroItem) heroSelect.getItemAtPosition(i);
        int id = heroItem.id;
        Intent intent = new Intent(getContext(), HeroDetail.class);
        intent.putExtra("HEROID", id);
        startActivity(intent);
    }
}
