package io.rocketfox.overwatchinfo.HTTPReq;

import io.rocketfox.overwatchinfo.Objects.HeroData;
import io.rocketfox.overwatchinfo.Objects.PatchNotes;

/**
 * Created by imp_lku on 04.01.2017.
 */

public interface AsyncResponseHeroData {
    void onLoadingDone(HeroData notes);
}
