package io.rocketfox.overwatchinfo.HTTPReq;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import io.rocketfox.overwatchinfo.Objects.Hero;
import io.rocketfox.overwatchinfo.Objects.HeroData;

public class HeroDetailReq extends AsyncTask<Void, Void, Hero> {

    public AsyncResponseHeroDetail delegate = null;
    private int id;

    public HeroDetailReq(AsyncResponseHeroDetail delegate, int id) {
        this.delegate = delegate;
        this.id = id;
    }

    public HeroDetailReq(int id) {
        this.id = id;
        this.delegate = null;
    }

    @Override
    protected Hero doInBackground(Void... params) {
        try {
            final String url = "https://overwatch-api.net/api/v1/hero/" + id;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Hero hero = restTemplate.getForObject(url, Hero.class);
            return hero;
        } catch (Exception e) {
            Log.e("HttpRequest", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Hero hero) {
        if (delegate != null)
            delegate.onLoadingDone(hero);
    }

}


