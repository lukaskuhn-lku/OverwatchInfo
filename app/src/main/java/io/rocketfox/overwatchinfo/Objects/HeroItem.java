package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 04.01.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeroItem {
    public int id;
    public String name;

    public HeroItem(){}
}
