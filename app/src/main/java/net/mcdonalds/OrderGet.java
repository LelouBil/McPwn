package net.mcdonalds;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderGet {
    @SerializedName("items")
    private List<OrderItemGet> items;

    public List<OrderItemGet> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItemGet> list) {
        this.items = list;
    }

    public List<OrderItem> getGenericItems() {
        List<OrderItem> arrayList = new ArrayList<>();
        if (this.items != null && this.items.size() > 0) {
            arrayList.addAll(this.items);
        }
        return arrayList;
    }
}
