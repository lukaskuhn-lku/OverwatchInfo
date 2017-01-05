package io.rocketfox.overwatchinfo.HTTPReq;

import io.rocketfox.overwatchinfo.Objects.PatchNotes;

/**
 * Created by Lukas on 05.01.2017.
 */
public interface AsyncResponsePatchNotes {
    void onLoadingDone(PatchNotes notes);
}
