package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class CLBooster {
    @SerializedName("endAt")
    public Date dateEndAt;
    @SerializedName("startAt")
    public Date dateStartAt;
    @SerializedName("details")
    public ArrayList<CLDetails> details = new ArrayList<>();
    @SerializedName("multiplier")
    public Long multiplier;
}
