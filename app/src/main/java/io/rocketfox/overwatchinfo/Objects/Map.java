package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 20.01.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Map {
    public int id;
    public String name;
}
