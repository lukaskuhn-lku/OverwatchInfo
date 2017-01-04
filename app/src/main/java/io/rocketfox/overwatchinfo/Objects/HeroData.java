package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Lukas on 04.01.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeroData {
    public ArrayList<Hero> data;
}
