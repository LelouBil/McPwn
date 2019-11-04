package net.mcdonalds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Delivery implements Serializable {
    @SerializedName("deliveryEstimatedDuration")
    public Integer deliveryEstimatedDuration;
    @SerializedName("deliveryEstimatedDurationWithTraffic")
    public Integer deliveryEstimatedDurationWithTraffic;
    @SerializedName("etaToDestination")
    public String etaToDestination;
    @SerializedName("status")
    public String status;
    @SerializedName("deliveryAddress")
    @Expose
    private DeliveryAddress deliveryAddress;
    @SerializedName("device")
    private Device device;
    @SerializedName("partnerJob")
    @Expose
    private PartnerJob partnerJob;

    public static String convertStepToStatus(int i) {
        switch (i) {
            case 0:
                return Order.OrderC.DELIVERY_STATUS_PENDING;
            case 1:
                return Order.OrderC.DELIVERY_STATUS_PICKING;
            case 2:
                return Order.OrderC.DELIVERY_STATUS_ALMOST_PICKING;
            case 3:
                return Order.OrderC.DELIVERY_STATUS_DELIVERING;
            case 4:
                return Order.OrderC.DELIVERY_STATUS_ALMOST_DELIVERING;
            case 5:
                return Order.OrderC.DELIVERY_STATUS_DELIVERED;
            default:
                switch (i) {
                    case 10:
                        return Order.OrderC.DELIVERY_STATUS_CANCELLED;
                    case 11:
                        return Order.OrderC.DELIVERY_STATUS_VOIDED;
                    case 12:
                        return "EXPIRED";
                    default:
                        return null;
                }
        }
    }

    public static String getStepName(int i) {
        switch (i) {
            case 0:
                return "RECHERCHE DU LIVREUR";
            case 1:
                return "LIVREUR EN DIRECTION DU RESTAURANT";
            case 2:
                return "COMMANDE EN PRÉPARATION";
            case 3:
                return "LIVREUR EN ROUTE";
            case 4:
                return "LIVREUR EN APPROCHE";
            case 5:
                return "VOUS ÊTES LIVRÉ";
            default:
                switch (i) {
                    case 10:
                    case 11:
                    case 12:
                        return "LIVRAISON ANNULÉE";
                    default:
                        return "";
                }
        }
    }

    private static int convertStatusToStep(String str) {
        if (Order.OrderC.DELIVERY_STATUS_PENDING.equalsIgnoreCase(str)) {
            return 0;
        }
        if (Order.OrderC.DELIVERY_STATUS_PICKING.equalsIgnoreCase(str)) {
            return 1;
        }
        if (Order.OrderC.DELIVERY_STATUS_ALMOST_PICKING.equalsIgnoreCase(str) || Order.OrderC.DELIVERY_STATUS_WAITING_AT_PICKUP.equalsIgnoreCase(str)) {
            return 2;
        }
        if (Order.OrderC.DELIVERY_STATUS_DELIVERING.equalsIgnoreCase(str)) {
            return 3;
        }
        if (Order.OrderC.DELIVERY_STATUS_WAITING_AT_DROPOFF.equalsIgnoreCase(str) || Order.OrderC.DELIVERY_STATUS_ALMOST_DELIVERING.equalsIgnoreCase(str)) {
            return 4;
        }
        if (Order.OrderC.DELIVERY_STATUS_DELIVERED.equalsIgnoreCase(str)) {
            return 5;
        }
        if (Order.OrderC.DELIVERY_STATUS_CANCELLED.equalsIgnoreCase(str)) {
            return 10;
        }
        if (Order.OrderC.DELIVERY_STATUS_VOIDED.equalsIgnoreCase(str)) {
            return 11;
        }
        return "EXPIRED".equalsIgnoreCase(str) ? 12 : -1;
    }

    int getStep() {
        return convertStatusToStep(this.status);
    }
}
