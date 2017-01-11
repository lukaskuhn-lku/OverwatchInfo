package io.rocketfox.overwatchinfo.Objects.HeroExtra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 11.01.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ability {
    public int id;
    public String name;
    public String description;
    public boolean is_ultimate;
}
