package io.rocketfox.overwatchinfo.HTTPReq;


import io.rocketfox.overwatchinfo.Player.Player;

/**
 * Created by imp_lku on 25.12.2016.
 */

public interface AsyncResponsePlayer {
    void processFinish(Player player);
}
