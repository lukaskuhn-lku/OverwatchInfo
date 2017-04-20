package io.rocketfox.overwatchinfo.HTTPReq;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import io.rocketfox.overwatchinfo.Player.Player;


/**
 * Created by imp_lku on 25.12.2016.
 */

public class PlayerReq extends AsyncTask<Void, Void, Player> {

    String name;
    public AsyncResponsePlayer delegate = null;

    public PlayerReq(String name, AsyncResponsePlayer delegate){
        this.name = name;
        this.delegate = delegate;
    }

    public PlayerReq(String name){
        this.name = name;
        this.delegate = null;
    }

    @Override
    protected Player doInBackground(Void...params){
        try{
            final String url= "https://owapi.net/api/v3/u/" + name + "/blob";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Player player = restTemplate.getForObject(url, Player.class);
            return player;
        }catch(Exception e){
            Log.e("HttpRequest", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Player player)
    {
        if(delegate != null)
           delegate.processFinish(player);
    }

}