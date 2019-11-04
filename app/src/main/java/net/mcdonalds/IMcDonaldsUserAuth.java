package net.mcdonalds;

import net.leloubil.mcpwn.mcapi.UserInfo;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IMcDonaldsUserAuth {
    @PUT("/api/customer/activate")
    @Headers({"Content-Type: application/json"})
    Call<Object> activateUser(@Header("Authorization") String str, @Query("email") String str2, @Query("hash") String str3, @Body String str4);

    /*@PUT("/api/order/{ref}/cancel")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<OrderGet> cancelOrder(@Header("Authorization") String str, @Path("ref") String str2, @Body String str3);

    @POST("/api/customer/checkCredentials")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<User> checkCredentials(@Header("Authorization") String str, @Body CheckCredential checkCredential);

    @POST("/api/order/check")
    @Headers({"Content-Type: application/json"})
    Call<OrderCreateResponse> checkOrder(@Header("Authorization") String str, @Body OrderPost orderPost, @Query("responseGroups") List<String> list);

    @POST("/api/order")
    @Headers({"Content-Type: application/json"})
    Call<OrderCreateResponse> createOrder(@Header("Authorization") String str, @Body OrderPost orderPost);

    @POST("/api/customer/create")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<User> createUser(@Header("Authorization") String str, @Body User user);

    @PUT("/api/customer/deleteExternalRefCard")
    @Headers({"Content-Type: application/json"})
    Call<PhysicalFidCardResponse> deleteExternalRefCard(@Header("Authorization") String str, @Query("externalRefCard") String str2, @Query("responseGroups") List<String> list, @Body String str3);

    @GET("/api/customer/findByExternalRef")
    @Headers({"Content-Type: application/json"})
    Call<PhysicalFidCardResponse> findByExternalRef(@Header("Authorization") String str, @Query("externalRef") String str2, @Query("responseGroups") List<String> list);

    @GET("/api/order/{ref}/delivery/phone")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<PhoneProxyResponse> getDeliveryPhoneProxy(@Header("Authorization") String str, @Path("ref") String str2);

    @GET("/api/order/{ref}/delivery/update")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<DeliveryPositionResponse> getDeliveryPosition(@Header("Authorization") String str, @Path("ref") String str2);

    @GET("/api/payment/initPayment")
    @Headers({"Content-Type: application/json"})
    Call<String> initPayment(@Header("Authorization") String str, @Query("paymentType") String str2, @Query("orderRef") String str3, @Query("redirectionUrl") String str4);

    @POST("/api/order/{ref}/delivery/reload")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<ReloadOrderGet> reloadOrder(@Header("Authorization") String str, @Path("ref") String str2, @Query("responseGroups") String str3, @Body String str4);
*/
    @GET("/api/order/customer/{ref}")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<List<OrderGet>> retrieveAuthorizedOrders(@Header("Authorization") String str, @Path("ref") String str2, @Query("status") String str3);

    @GET("/api/order")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<List<OrderGet>> retrieveFavoriteOrders(@Header("Authorization") String str, @Query("customerRef") String str2, @Query("favorite") boolean z, @Query("responseGroups") String str3);

    @GET("/api/order/customer/{ref}")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<List<OrderGet>> retrieveFirstAuthorizedOrders(@Header("Authorization") String str, @Path("ref") String str2, @Query("nbPerPage") int i, @Query("status") String str3);

    @GET("/api/order")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<List<OrderGet>> retrieveOrder(@Header("Authorization") String str, @Query("customerRef") String str2, @Query("orderNumber") String str3, @Query("responseGroups") String str4);

    @GET("/api/order")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<List<OrderGet>> retrieveOrders(@Header("Authorization") String str, @Query("customerRef") String str2, @Query("responseGroups") String str3);

    @GET("/api/order/customer/{ref}")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<List<OrderGet>> retrievePaidOrders(@Header("Authorization") String str, @Path("ref") String str2, @Query("nbPerPage") int i, @Query("status") String str3);

    /*
        @GET("/api/payment/paymentTypes")
        @Headers({"Content-Type: application/json"})
        Call<List<String>> retrievePaymentTypes(@Query("restaurantRef") String str, @Query("eatType") String str2);
    */
    @GET("/api/customer")
    @Headers({"Content-Type: application/json"})
    Call<UserInfo> retrieveUserInformations(@Header("Authorization") String str, @Query("responseGroups") List<String> list);

    /*@GET("/api/delivery/{ref}/sendInvoice")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<Object> sendDeliveryInvoice(@Header("Authorization") String str, @Path("ref") String str2);

    @PUT("/api/customer/updateExternalRefCard")
    @Headers({"Content-Type: application/json"})
    Call<PhysicalFidCardResponse> updateExternalRefCard(@Header("Authorization") String str, @Query("externalRef") String str2, @Query("externalRefCard") String str3, @Query("responseGroups") List<String> list, @Body String str4);

    @PUT("/api/order/{ref}/favorite")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<OrderGet> updateOrder(@Header("Authorization") String str, @Path("ref") String str2, @Query("favorite") Boolean bool, @Query("favoriteName") String str3, @Body String str4);

    @PUT("/api/customer/{ref}")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<User> updateUser(@Path("ref") String str, @Header("Authorization") String str2, @Body User user);

    @PUT("/api/customer/changePassword")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<User> updateUserPassword(@Header("Authorization") String str, @Body UpdatePassword updatePassword);*/
}
