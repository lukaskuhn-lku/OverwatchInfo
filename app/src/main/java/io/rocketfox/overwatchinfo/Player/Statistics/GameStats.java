package io.rocketfox.overwatchinfo.Player.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 26.12.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameStats {
    private Double time_spent_on_fire;
    private int defensive_assists;
    private int solo_kills_most_in_game;
    private int eliminations;
    private int medals_gold;
    private int melee_final_blows;
    private int objective_kills_most_in_game;
    private int healing_done;
    private int games_played;
    private int deaths;
    private double objective_time_most_in_game;
    private int healing_done_most_in_game;
    private int cards;
    private int games_lost;
    private int objective_kills;
    private int solo_kills;
    private int medals_bronze;
    private int multikill_best;
    private double objective_time;
    private int offensive_assists_most_in_game;
    private int games_won;
    private double time_spent_on_fire_most_in_game;
    private int damage_done;
    private int final_blows_most_in_game;
    private int medals_silver;
    private int eliminations_most_in_game;
    private int damage_done_most_in_game;
    private int multikills;
    private int environmental_deaths;
    private int melee_final_blows_most_in_game;
    private int medals;
    private double kpd;
    private int offensive_assists;
    private int enviromental_kills;
    private int final_blows;
    private int games_tied;
    private int time_played;
    private int defensive_assists_most_in_game;

    public Double getTime_spent_on_fire() {
        return time_spent_on_fire;
    }

    public int getDefensive_assists() {
        return defensive_assists;
    }

    public int getSolo_kills_most_in_game() {
        return solo_kills_most_in_game;
    }

    public int getEliminations() {
        return eliminations;
    }

    public int getMedals_gold() {
        return medals_gold;
    }

    public int getMelee_final_blows() {
        return melee_final_blows;
    }

    public int getObjective_kills_most_in_game() {
        return objective_kills_most_in_game;
    }

    public int getHealing_done() {
        return healing_done;
    }

    public int getGames_played() {
        return games_played;
    }

    public int getDeaths() {
        return deaths;
    }

    public double getObjective_time_most_in_game() {
        return objective_time_most_in_game;
    }

    public int getHealing_done_most_in_game() {
        return healing_done_most_in_game;
    }

    public int getCards() {
        return cards;
    }

    public int getGames_lost() {
        return games_lost;
    }

    public int getObjective_kills() {
        return objective_kills;
    }

    public int getSolo_kills() {
        return solo_kills;
    }

    public int getMedals_bronze() {
        return medals_bronze;
    }

    public int getMultikill_best() {
        return multikill_best;
    }

    public double getObjective_time() {
        return objective_time;
    }

    public int getOffensive_assists_most_in_game() {
        return offensive_assists_most_in_game;
    }

    public int getGames_won() {
        return games_won;
    }

    public double getTime_spent_on_fire_most_in_game() {
        return time_spent_on_fire_most_in_game;
    }

    public int getDamage_done() {
        return damage_done;
    }

    public int getFinal_blows_most_in_game() {
        return final_blows_most_in_game;
    }

    public int getMedals_silver() {
        return medals_silver;
    }

    public int getEliminations_most_in_game() {
        return eliminations_most_in_game;
    }

    public int getDamage_done_most_in_game() {
        return damage_done_most_in_game;
    }

    public int getMultikills() {
        return multikills;
    }

    public int getEnvironmental_deaths() {
        return environmental_deaths;
    }

    public int getMelee_final_blows_most_in_game() {
        return melee_final_blows_most_in_game;
    }

    public int getMedals() {
        return medals;
    }

    public double getKpd() {
        return kpd;
    }

    public int getOffensive_assists() {
        return offensive_assists;
    }

    public int getEnviromental_kills() {
        return enviromental_kills;
    }

    public int getFinal_blows() {
        return final_blows;
    }

    public int getGames_tied() {
        return games_tied;
    }

    public int getTime_played() {
        return time_played;
    }

    public int getDefensive_assists_most_in_game() {
        return defensive_assists_most_in_game;
    }
}
