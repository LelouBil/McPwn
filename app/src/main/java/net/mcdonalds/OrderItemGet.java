package net.mcdonalds;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderItemGet extends OrderItem {
    @SerializedName("choices")
    public List<OrderItemGet> choices;
    @SerializedName("excludedIngredients")
    public List<McdoIngredient> excludedIngredients = new ArrayList<>();
    @SerializedName("primaryOrderItem")
    public OrderItemGet primaryOrderItem;
    @SerializedName("secondaryOrderItem")
    public OrderItemGet secondaryOrderItem;
}
