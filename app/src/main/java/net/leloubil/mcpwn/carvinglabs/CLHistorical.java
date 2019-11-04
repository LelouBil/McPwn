package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.Date;

@Getter
public class CLHistorical {
    @SerializedName("createdAt")
    public Date createdAt;
    @SerializedName("earnProduct")
    public CLProduct earnProduct;
    @SerializedName("id")
    public Long id;
    @SerializedName("membershipId")
    public Long menbershipId;
    @SerializedName("order")
    public CLOrder order;
    @SerializedName("totalBurntPoints")
    public Long totalBurntPoints;
    @SerializedName("type")
    public String type;
    @SerializedName("value")
    public Long value;
}
