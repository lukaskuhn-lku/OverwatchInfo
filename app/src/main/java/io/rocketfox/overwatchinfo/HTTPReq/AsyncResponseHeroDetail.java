package io.rocketfox.overwatchinfo.HTTPReq;

import io.rocketfox.overwatchinfo.Objects.Hero;
import io.rocketfox.overwatchinfo.Objects.HeroData;

/**
 * Created by Lukas on 11.01.2017.
 */

public interface AsyncResponseHeroDetail {
    void onLoadingDone(Hero hero);
}
