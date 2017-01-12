package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import io.rocketfox.overwatchinfo.Objects.HeroExtra.Ability;
import io.rocketfox.overwatchinfo.Objects.HeroExtra.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero {
    public String name;
    public String real_name;
    public int id;
    public int health;
    public int armour;
    public int shield;
    public int age;
    public String description;
    public String height;
    public String affiliation;
    public String base_of_operations;
    public int difficulty;
    public Role role;
    public ArrayList<Ability> abilities;
}
