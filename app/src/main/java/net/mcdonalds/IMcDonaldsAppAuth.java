package net.mcdonalds;

public interface IMcDonaldsAppAuth {
    /*@PUT("/api/customer/forget/change")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<Object> changePassword(@Header("Authorization") String str, @Body Password password);

    @POST("/api/customer/checkPassword")
    @Headers({"Content-Type: application/json"})
    Call<Object> checkPassword(@Body User user);*/

   /* @POST("/api/restaurant/{ref}/address/checkeligibility")
    @Headers({"Content-Type: application/json"})
    Call<DeliveryAddressEligibility> checkeligibility(@Header("Authorization") String str, @Path("ref") String str2, @Body AddressCustomer addressCustomer);

    @POST("/api/customer/create/prospect")
    @Headers({"Content-Type: application/json"})
    Call<User> createProspect(@Header("Authorization") String str, @Body User user);

    @DELETE("/api/customer/{ref}/namedaddress/{addressTitle}")
    @Headers({"Content-Type: application/json"})
    Call<Object> deleteDeliveryAddress(@Header("Authorization") String str, @Path("ref") String str2, @Path("addressTitle") String str3);

    @POST("/api/customer/{ref}/create/pending")
    @Headers({"Content-Type: application/json"})
    Call<User> finalizeProspect(@Path("ref") String str, @Header("Authorization") String str2, @Body User user);

    @GET("/api/customer/findByEmail")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<User> findByEmail(@Header("Authorization") String str, @Query("email") String str2, @Query("responseGroups") List<String> list);

    @POST("/api/customer/forget")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<Object> forgetPassword(@Header("Authorization") String str, @Query("email") String str2, @Body String str3);

    @GET("/api/customer/{ref}/namedaddress")
    @Headers({"Content-Type: application/json"})
    Call<List<AddressCustomer>> getDeliveryAddress(@Header("Authorization") String str, @Path("ref") String str2);

    @POST("/api/customer/{ref}/address")
    @Headers({"Content-Type: application/json"})
    Call<AddressCustomer> postDeliveryAddress(@Header("Authorization") String str, @Path("ref") String str2, @Body AddressCustomer addressCustomer);

    @PUT("/api/customer/{ref}/namedaddress/{addressTitle}")
    @Headers({"Content-Type: application/json"})
    Call<AddressCustomer> putDeliveryAddress(@Header("Authorization") String str, @Path("ref") String str2, @Path("addressTitle") String str3, @Body AddressCustomer addressCustomer);

    @POST("/api/customer/resetPassword")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache, no-store, must-revalidate"})
    Call<Object> resetPassword(@Header("Authorization") String str, @Query("email") String str2, @Body String str3);

    @POST("/api/customer/sendmail")
    @Headers({"Content-Type: application/json"})
    Call<Object> sendMail(@Header("Authorization") String str, @Query("email") String str2, @Body String str3);

    @POST("/api/customer/sendmail/{ref}")
    @Headers({"Content-Type: application/json"})
    Call<Object> sendMailByRef(@Path("ref") String str, @Header("Authorization") String str2, @Body String str3);

    @PUT("/api/customer/{ref}/create")
    @Headers({"Content-Type: application/json"})
    Call<User> updateProspect(@Path("ref") String str, @Header("Authorization") String str2, @Body User user);*/
}
