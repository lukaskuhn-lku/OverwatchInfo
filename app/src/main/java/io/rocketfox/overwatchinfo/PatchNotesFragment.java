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

import java.util.ArrayList;
import java.util.List;

import io.rocketfox.overwatchinfo.HTTPReq.PatchNotesReq;
import io.rocketfox.overwatchinfo.Objects.PatchNote;
import io.rocketfox.overwatchinfo.Objects.PatchNotes;
import io.rocketfox.overwatchinfo.adapters.PatchNoteAdapter;


public class PatchNotesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PatchNotesFragment() {}

    public static PatchNotesFragment newInstance() {
        PatchNotesFragment fragment = new PatchNotesFragment();
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

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_notes);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<PatchNote> notes = LoadPatchNotes();

        mAdapter = new PatchNoteAdapter(notes);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<PatchNote> LoadPatchNotes() {
        try {
            PatchNotes restDump = new PatchNotesReq().execute().get();
            ArrayList<PatchNote> notes = new ArrayList<PatchNote>();
            for(PatchNote note:restDump.patchNotes){
                notes.add(note);
            }
            return notes;
        }catch(Exception e){

        }
        return null;
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


}
