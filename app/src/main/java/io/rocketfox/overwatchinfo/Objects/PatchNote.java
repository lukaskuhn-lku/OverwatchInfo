package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatchNote {
    public String patchVersion;
    public String status;
    public String detail;

    public PatchNote(){}

    public PatchNote(String patchVersion, String status, String detail){
        this.patchVersion = patchVersion;
        this.status = status;
        this.detail = detail;
    }
}
