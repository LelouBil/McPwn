package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class CLReward {
    @SerializedName("burnProducts")
    public ArrayList<CLProduct> burnProducts = new ArrayList<>();
    @SerializedName("channels")
    public ArrayList<String> channels = new ArrayList<>();
    @SerializedName("consumption")
    public ArrayList<CLConsumption> consumption = new ArrayList<>();
    @SerializedName("createdAt")
    public Date createdAt;
    @SerializedName("expiresAt")
    public Date expiresAt;
    @SerializedName("id")
    public Long id;
    @SerializedName("membershipId")
    public Long membershipId;
    @SerializedName("nbPoints")
    public Long nbPoints;
    @SerializedName("restaurants")
    public ArrayList<String> restaurants = new ArrayList<>();
    @SerializedName("usedAt")
    public Date usedAt;
}
