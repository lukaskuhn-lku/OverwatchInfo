package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponsePatchNotes;
import io.rocketfox.overwatchinfo.HTTPReq.PatchNotesReq;
import io.rocketfox.overwatchinfo.Objects.PatchNote;
import io.rocketfox.overwatchinfo.Objects.PatchNotes;
import io.rocketfox.overwatchinfo.adapters.PatchNoteAdapter;


public class PatchNotesFragment extends Fragment implements AsyncResponsePatchNotes{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar loadingBar;

    public PatchNotesFragment() {}

    public static PatchNotesFragment newInstance() {
        PatchNotesFragment fragment = new PatchNotesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Patch Notes & Updates");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



        View v = getView();

        loadingBar = (ProgressBar) v.findViewById(R.id.loadingBarPatchNotes);

        try {
            loadingBar.setVisibility(View.VISIBLE);
            new PatchNotesReq(this).execute();
        }catch(Exception e){
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_notes);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patch_notes, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onLoadingNotesDone(PatchNotes notes) {
        loadingBar.setVisibility(View.GONE);
        ArrayList<PatchNote> noteList = new ArrayList<>();

        for(PatchNote note:notes.patchNotes){
            noteList.add(note);
        }

        mAdapter = new PatchNoteAdapter(noteList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
