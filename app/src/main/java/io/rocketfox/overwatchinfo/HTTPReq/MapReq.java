package io.rocketfox.overwatchinfo.HTTPReq;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import io.rocketfox.overwatchinfo.Objects.HeroData;
import io.rocketfox.overwatchinfo.Objects.MapData;

public class MapReq extends AsyncTask<Void, Void, MapData> {

    public AsyncResponseMaps delegate = null;

    public MapReq(AsyncResponseMaps delegate) {
        this.delegate = delegate;
    }

    public MapReq() {
        this.delegate = null;
    }

    @Override
    protected MapData doInBackground(Void... params) {
        try {
            final String url = "https://overwatch-api.net/api/v1/map";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            MapData data = restTemplate.getForObject(url, MapData.class);
            return data;
        } catch (Exception e) {
            Log.e("HttpRequest", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(MapData data) {
        if (delegate != null)
            delegate.onLoadingDone(data);
    }

}

