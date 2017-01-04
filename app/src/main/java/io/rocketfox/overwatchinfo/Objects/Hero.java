package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 04.01.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero {
    public int id;
    public String name;
    public String description;
    public int health;
    public int armour;
    public String real_name;
    public String age;
    public String height;
    public String affiliation;
    public String base_of_operations;
    public String difficulty;
    public String url;
}
