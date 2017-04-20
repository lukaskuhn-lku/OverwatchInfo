package io.rocketfox.overwatchinfo.Player.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 23.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {
    private CompetitveStats competitive;

    public CompetitveStats getCompetitive() {
        return competitive;
    }
}
