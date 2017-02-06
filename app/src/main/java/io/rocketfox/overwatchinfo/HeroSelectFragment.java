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
import android.widget.ProgressBar;


import java.util.ArrayList;

import io.paperdb.Paper;
import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponseHeroData;
import io.rocketfox.overwatchinfo.HTTPReq.HeroReq;
import io.rocketfox.overwatchinfo.Objects.HeroData;
import io.rocketfox.overwatchinfo.Objects.HeroItem;
import io.rocketfox.overwatchinfo.adapters.HeroSelectAdapter;


public class HeroSelectFragment extends Fragment implements AsyncResponseHeroData, AdapterView.OnItemClickListener {

    private HeroData heroData;
    private Context context;
    private ListView heroSelect;
    ProgressBar loadingBar;
    public HeroSelectFragment() {
    }

    public static HeroSelectFragment newInstance() {
        HeroSelectFragment fragment = new HeroSelectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(getContext());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        heroSelect = (ListView) getView().findViewById(R.id.list_view_heroes);
        loadingBar = (ProgressBar) getView().findViewById(R.id.loadingBarSelect);

        try {
            loadingBar.setVisibility(View.VISIBLE);
           new HeroReq(this).execute();
        }catch(Exception e){
            e.printStackTrace();
        }

        HeroData data = Paper.book().read("HeroData");
        if(data != null){
            updateUI(data);
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
        try {
            updateUI(data);
            Paper.book().write("HeroData", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUI(HeroData heroData) {
        loadingBar.setVisibility(View.GONE);

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
