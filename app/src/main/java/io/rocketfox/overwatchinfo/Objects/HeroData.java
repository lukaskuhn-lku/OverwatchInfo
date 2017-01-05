package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Lukas on 04.01.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeroData {
    private ArrayList<HeroItem> data;

    public ArrayList<HeroItem> getData() {
        return data;
    }

    public void setData(ArrayList<HeroItem> data) {
        this.data = data;
    }
}
