package io.rocketfox.overwatchinfo.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.rocketfox.overwatchinfo.Objects.PatchNote;
import io.rocketfox.overwatchinfo.R;

/**
 * Created by imp_lku on 04.01.2017.
 */
public class PatchNoteAdapter extends RecyclerView.Adapter<PatchNoteAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextViewStatus;
        public TextView mTextViewDetail;
        public TextView mTextViewVersion;
        public ViewHolder(View v) {
            super(v);
            mTextViewStatus = (TextView) v.findViewById(R.id.cardNotes_textViewStatus);
            mTextViewDetail = (TextView) v.findViewById(R.id.cardNotes_textViewDetail);
            mTextViewVersion = (TextView) v.findViewById(R.id.cardNotes_textViewVersion);
        }
    }

    private ArrayList<PatchNote> notes;

    @Override
    public PatchNoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_patch_note, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextViewStatus.setText(notes.get(position).status);
        holder.mTextViewVersion.setText(notes.get(position).patchVersion);

        String fullText = notes.get(position).detail;

        holder.mTextViewDetail.setText(Html.fromHtml(fullText));
        holder.mTextViewDetail.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public PatchNoteAdapter(ArrayList<PatchNote> notes) {
        this.notes = notes;
    }
}
