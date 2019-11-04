package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Capping implements Serializable {
    @SerializedName("displayLabel")
    public String displayLabel;
    @SerializedName("id")
    public String id;
    @SerializedName("maxLimitValue")
    public Integer maxLimitValue;
}
