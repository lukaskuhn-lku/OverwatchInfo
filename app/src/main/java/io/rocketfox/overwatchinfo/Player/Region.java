package io.rocketfox.overwatchinfo.Player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.rocketfox.overwatchinfo.Player.Statistics.Stats;


/**
 * Created by Lukas on 23.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {
    private Stats stats;

    public Stats getStats() {
        return stats;
    }
}
