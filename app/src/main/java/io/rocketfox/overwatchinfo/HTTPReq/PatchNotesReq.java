package io.rocketfox.overwatchinfo.HTTPReq;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import io.rocketfox.overwatchinfo.Objects.PatchNote;
import io.rocketfox.overwatchinfo.Objects.PatchNotes;

/**
 * Created by imp_lku on 04.01.2017.
 */

public class PatchNotesReq extends AsyncTask<Void, Void, PatchNotes> {

    public AsyncResponse delegate = null;

    public PatchNotesReq(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    public PatchNotesReq() {
        this.delegate = null;
    }

    @Override
    protected PatchNotes doInBackground(Void... params) {
        try {
            final String url = "https://api.lootbox.eu/patch_notes";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            PatchNotes notes = restTemplate.getForObject(url, PatchNotes.class);
            return notes;
        } catch (Exception e) {
            Log.e("HttpRequest", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(PatchNotes notes) {
        if (delegate != null)
            delegate.onLoadingDone(notes);
    }

}

