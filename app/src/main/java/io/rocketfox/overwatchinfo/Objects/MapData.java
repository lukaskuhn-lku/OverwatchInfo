package io.rocketfox.overwatchinfo.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapData {
    public ArrayList<Map> data;
}
