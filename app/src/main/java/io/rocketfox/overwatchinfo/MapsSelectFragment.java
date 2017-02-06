package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import io.paperdb.Paper;
import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponseMaps;
import io.rocketfox.overwatchinfo.HTTPReq.MapReq;
import io.rocketfox.overwatchinfo.Objects.MapData;
import io.rocketfox.overwatchinfo.adapters.MapSelectAdapter;
import io.rocketfox.overwatchinfo.adapters.NewsCardAdapter;

public class MapsSelectFragment extends Fragment implements AsyncResponseMaps {


    public MapsSelectFragment() {

    }

    public static MapsSelectFragment newInstance() {
        MapsSelectFragment fragment = new MapsSelectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps_select, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    RecyclerView recyclerView;
    ProgressBar loadingBar;
    @Override
    public void onViewCreated(View v, Bundle savedInstance){
        super.onViewCreated(v, savedInstance);

        loadingBar = (ProgressBar) v.findViewById(R.id.loadingBarMaps);
        loadingBar.setVisibility(View.VISIBLE);

        if(Paper.book().read("Maps") != null)
            UpdateRecyclerView((MapData) Paper.book().read("Maps"));

        new MapReq(this).execute();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_maps);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onLoadingDone(MapData data) {
        Paper.book().write("Maps", data);
        UpdateRecyclerView(data);
    }

    private void UpdateRecyclerView(MapData data) {
    try {
        loadingBar.setVisibility(View.GONE);
        MapSelectAdapter adapter = new MapSelectAdapter(data.data, getContext());
        recyclerView.setAdapter(adapter);
    }catch(Exception ex){
        ex.printStackTrace();
    }
    }
}
