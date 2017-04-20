package io.rocketfox.overwatchinfo.Player;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Lukas on 23.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private Region eu;
    private Region us;
    private Region kr;
    private Region any;
    private String RegionTag;

    private Bitmap avatarCache;

    private String name;

    public Region getEu() {
        return eu;
    }

    public Region getUs() {
        return us;
    }

    public Region getKr() {
        return kr;
    }

    public Region getAny() {
        return any;
    }


    /**
     * Better call that before calling RegionTag haha
     * Rest regions are null btw
     * @return Players Region
     */
    public Region getRegion() {
        if (eu != null) {
            this.RegionTag = "eu";
            return eu;
        }

        if (us != null) {
            this.RegionTag = "us";
            return us;
        }

        if (kr != null) {
            this.RegionTag = "kr";
            return kr;
        }

        if (any != null) {
            this.RegionTag = "any";
            return any;
        }

        return null;
    }


    /**
     * Region Tag is "undefined" if getRegion() was not executed once
     * @return RegionTag as String (eu, usm, kr, any)
     */
    public String getRegionTag() {
        return RegionTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getAvatarCache() {
        return avatarCache;
    }

    public void setAvatarCache(Bitmap avatarCache) {
        this.avatarCache = avatarCache;
    }
}
