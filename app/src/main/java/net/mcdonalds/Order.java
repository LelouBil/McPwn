package net.mcdonalds;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public abstract class Order implements Serializable {
    public static final String CANCELED = "CANCELED";
    public static final String ERROR = "ERROR";
    public static final String EXPIRED = "EXPIRED";
    public static final String NEW = "NEW";
    public static final String ORIGIN_COUNTER = "TILL";
    public static final String ORIGIN_DRIVE = "DRIVE";
    public static final String ORIGIN_KIOSK = "KIOSK";
    public static final String ORIGIN_WEB = "WEB";
    public static final String PAID = "PAID";
    public static final String PAYMENT_AUTHORIZED = "PAYMENT_AUTHORIZED";
    public static final String PAYMENT_ERROR = "PAYMENT_ERROR";
    public static final String RETRIEVED = "RETRIEVED";
    @SerializedName("deliveryAmount")
    public Integer deliveryAmount;
    @SerializedName("donationAmount")
    public Integer donationAmount;
    @SerializedName("customerRef")
    private String customerRef;
    @SerializedName("delivery")
    private Delivery delivery;
    @SerializedName("eatType")
    private String eatType;
    @SerializedName("favoriteName")
    private String favoriteName;
    @SerializedName("favorite")
    private boolean isFav;
    @SerializedName("orderNumber")
    private String orderNumber;
    @SerializedName("ref")
    private String orderRef;
    @SerializedName("status")
    private String orderStatus;
    @SerializedName("orderedDate")
    private String orderedDate;
    @SerializedName("origin")
    private String origin;
    @SerializedName("restaurantRef")
    private String restaurantRef;
    @SerializedName("securityKey")
    private String securityKey;
    @SerializedName("totalAmount")
    private Integer totalAmount;
    @SerializedName("totalAmountWithDelivery")
    private Integer totalAmountWithDelivery;
    @SerializedName("workflowType")
    private String workflowType;

    public abstract List<OrderItem> getGenericItems();

    public Integer getTotalAmount() {
        if (this.totalAmount != null) {
            return this.totalAmount;
        }
        return 0;


    }

    public boolean hasDonation() {
        return this.donationAmount != null && this.donationAmount > 0;
    }

    public Integer getDeliveryAmount() {
        if (this.deliveryAmount != null) {
            return this.deliveryAmount;
        }
        return 0;
    }

    public Integer getTotalAmountWithDelivery() {
        if (this.totalAmountWithDelivery != null) {
            return this.totalAmountWithDelivery;
        }
        return 0;
    }

    public boolean canCancel() {
        boolean z = true;
        if (getDelivery() == null) {
            return true;
        }
        if (getDelivery().getStep() != 0) {
            z = false;
        }
        return z;
    }

    public interface OrderC {
        String BASIC_PRODUCT_TYPE = "product";
        String DELIVERY = "HOME_DELIVERY";
        String DELIVERY_STATUS_ALMOST_DELIVERING = "ALMOST_DELIVERING";
        String DELIVERY_STATUS_ALMOST_PICKING = "ALMOST_PICKING";
        String DELIVERY_STATUS_CANCELLED = "CANCELLED";
        String DELIVERY_STATUS_DELIVERED = "DELIVERED";
        String DELIVERY_STATUS_DELIVERING = "DELIVERING";
        String DELIVERY_STATUS_EXPIRED = "EXPIRED";
        String DELIVERY_STATUS_PENDING = "PENDING";
        String DELIVERY_STATUS_PICKING = "PICKING";
        String DELIVERY_STATUS_VOIDED = "VOIDED";
        String DELIVERY_STATUS_WAITING_AT_DROPOFF = "WAITING_AT_DROPOFF";
        String DELIVERY_STATUS_WAITING_AT_PICKUP = "WAITING_AT_PICKUP";
        int DELIVERY_STEP_ALMOST_DELIVERING = 4;
        int DELIVERY_STEP_ALMOST_PICKING = 2;
        int DELIVERY_STEP_CANCELLED = 10;
        int DELIVERY_STEP_DELIVERED = 5;
        int DELIVERY_STEP_DELIVERING = 3;
        int DELIVERY_STEP_EXPIRED = 12;
        int DELIVERY_STEP_PENDING = 0;
        int DELIVERY_STEP_PICKING = 1;
        int DELIVERY_STEP_VOIDED = 11;
        String EAT_IN_PARAMETER = "EAT_IN";
        String INTERNET_PAID_TYPE = "INTERNET_PAID";
        int MAXIMUM_ORDER_AMOUNT = 200;
        String PAYMENT_CARD_CALLBACK = "/api/payment/sipsAuthorizationCallBack";
        String PAYMENT_PAYPAL_CALLBACK = "/api/payment/paypalAuthorizationCallBack";
        String QR_CODE_URL = "/api/order/%s/%s/code";
        String TAKE_AWAY_PARAMETER = "TAKE_OUT";
        String WORKFLOW_TYPE = "INTERNET_PAID";
    }

}
