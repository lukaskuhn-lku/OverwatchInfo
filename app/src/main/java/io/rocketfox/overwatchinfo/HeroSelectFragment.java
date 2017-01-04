package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import io.rocketfox.overwatchinfo.HTTPReq.HeroReq;
import io.rocketfox.overwatchinfo.Objects.HeroData;



public class HeroSelectFragment extends Fragment {

    private HeroData heroData;

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

        View v = getView();

        try {
            heroData = new HeroReq().execute().get();
        }catch(Exception e){
            e.printStackTrace();
        }

        ListView heroSelect = (ListView) v.findViewById(R.id.list_view_heroes);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hero_select, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
