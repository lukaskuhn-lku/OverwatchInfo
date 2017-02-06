package io.rocketfox.overwatchinfo.HTTPReq;

import java.util.ArrayList;

import io.rocketfox.overwatchinfo.Objects.Map;
import io.rocketfox.overwatchinfo.Objects.MapData;

/**
 * Created by Lukas on 20.01.2017.
 */

public interface AsyncResponseMaps {
    void onLoadingDone(MapData data);
}
