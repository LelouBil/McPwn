package net.mcdonalds;

import com.google.gson.annotations.SerializedName;

public class OrderItem {
    @SerializedName("choiceRef")
    public String choiceRef;
    @SerializedName("label")
    public String label;
    @SerializedName("offered")
    public Boolean offered;
    @SerializedName("productRef")
    public String productRef;
    @SerializedName("promo")
    public Boolean promo;
    @SerializedName("qty")
    public int quantity;
    @SerializedName("rewardRef")
    public String rewardRef;
    @SerializedName("type")
    public String type;
    @SerializedName("unitAmount")
    public Integer unitAmount;
    @SerializedName("usedPoints")
    public Integer usedPoints;
}
