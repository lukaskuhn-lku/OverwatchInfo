package io.rocketfox.overwatchinfo.Player.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 23.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverallStats {
    private int losses;
    private int ties;
    private int comprank;
    private int games;
    private int level;
    private int wins;
    private String avatar;
    private int prestige;
    private int win_rate;

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public int getComprank() {
        return comprank;
    }

    public int getGames() {
        return games;
    }

    public int getLevel() {
        return level;
    }

    public int getWins() {
        return wins;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getPrestige() {
        return prestige;
    }

    public int getWin_rate() {
        return win_rate;
    }
}
