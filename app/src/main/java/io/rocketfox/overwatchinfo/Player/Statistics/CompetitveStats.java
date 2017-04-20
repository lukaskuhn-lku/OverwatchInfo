package io.rocketfox.overwatchinfo.Player.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 23.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitveStats {
    private Boolean competitive;
    private AverageStats average_stats;
    private OverallStats overall_stats;
    private GameStats game_stats;

    public Boolean getCompetitive() {
        return competitive;
    }

    public AverageStats getAverage_stats() {
        return average_stats;
    }

    public OverallStats getOverall_stats() {
        return overall_stats;
    }

    public GameStats getGame_stats() { return game_stats;}
}
