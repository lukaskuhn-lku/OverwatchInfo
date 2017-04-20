package io.rocketfox.overwatchinfo.Player.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 23.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AverageStats {
    private String time_spent_on_fire_avg;
    private String objective_kills_avg;
    private String solo_kills_avg;
    private String final_blows_avg;
    private String objective_time_avg;
    private String eliminations_avg;
    private String melee_final_blows_avg;
    private String defensive_assists_avg;
    private String damage_done_avg;
    private String deaths_avg;
    private String healing_done_avg;
    private String offensive_assists_avg;

    public String getTime_spent_on_fire_avg() {
        return time_spent_on_fire_avg;
    }

    public String getObjective_kills_avg() {
        return objective_kills_avg;
    }

    public String getSolo_kills_avg() {
        return solo_kills_avg;
    }

    public String getFinal_blows_avg() {
        return final_blows_avg;
    }

    public String getObjective_time_avg() {
        return objective_time_avg;
    }

    public String getEliminations_avg() {
        return eliminations_avg;
    }

    public String getMelee_final_blows_avg() {
        return melee_final_blows_avg;
    }

    public String getDefensive_assists_avg() {
        return defensive_assists_avg;
    }

    public String getDamage_done_avg() {
        return damage_done_avg;
    }

    public String getDeaths_avg() {
        return deaths_avg;
    }

    public String getHealing_done_avg() {
        return healing_done_avg;
    }

    public String getOffensive_assists_avg() {
        return offensive_assists_avg;
    }
}
