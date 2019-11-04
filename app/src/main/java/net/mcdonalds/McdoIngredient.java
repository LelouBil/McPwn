package net.mcdonalds;

import com.google.gson.annotations.SerializedName;

public class McdoIngredient {
    @SerializedName("customizable")
    public Boolean customizable = Boolean.valueOf(false);
    @SerializedName("label")
    public String label;
    @SerializedName("order")
    public Integer order;
    @SerializedName("ref")
    public String ref;
}
