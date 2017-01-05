package io.rocketfox.overwatchinfo.HTTPReq;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import io.rocketfox.overwatchinfo.Objects.HeroData;

/**
 * Created by imp_lku on 04.01.2017.
 */

public class HeroReq extends AsyncTask<Void, Void, HeroData> {

    public AsyncResponseHeroData delegate = null;

    public HeroReq(AsyncResponseHeroData delegate) {
        this.delegate = delegate;
    }

    public HeroReq() {
        this.delegate = null;
    }

    @Override
    protected HeroData doInBackground(Void... params) {
        try {
            final String url = "https://overwatch-api.net/api/v1/hero";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HeroData heros = restTemplate.getForObject(url, HeroData.class);
            return heros;
        } catch (Exception e) {
            Log.e("HttpRequest", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(HeroData heros) {
        if (delegate != null)
            delegate.onLoadingDone(heros);
    }

}

