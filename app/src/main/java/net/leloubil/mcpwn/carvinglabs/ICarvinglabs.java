package net.leloubil.mcpwn.carvinglabs;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ICarvinglabs {
    @DELETE("api/v1/user/{userId}/memberships/restaurant/{restaurantId}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<Object> deleteRestaurantSubScribes(@Header("Authorization") String str, @Path("userId") String str2, @Path("restaurantId") String str3);

    @HTTP(hasBody = true, method = "DELETE", path = "api/v1/user/{userFidId}/memberships")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<Object> deleteSubScribes(@Header("Authorization") String str, @Path("userFidId") String str2, @Body CLMemberShipIds cLMemberShipIds);

    @GET("api/v1/restaurant/{restaurantId}/programs/{loyaltyProgramId}/cgu?json=1")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<CLCGU> getCGUByProgram(@Header("Authorization") String str, @Path("restaurantId") String str2, @Path("loyaltyProgramId") Long l);

    @GET("api/v1/restaurant/{restaurantId}/programs/{loyaltyProgramId}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<CLProgram> getConsultationFID(@Header("Authorization") String str, @Path("restaurantId") String str2, @Path("loyaltyProgramId") Long l);

    @GET("api/v1/user/{userId}/rewards/{rewardId}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<CLReward> getDetailsReward(@Header("Authorization") String str, @Path("userId") String str2, @Path("rewardId") Long l);

    @GET("api/v1/user/{userId}/memberships/{membershipId}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<CLMemberShip> getDetailsSubscribe(@Header("Authorization") String str, @Path("userId") String str2, @Path("membershipId") Long l);

    @GET("api/v1/user/{userId}/rewards/{rewardId}/consumption")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLConsumption>> getHistoricalReward(@Header("Authorization") String str, @Path("userId") String str2, @Path("rewardId") Long l);

    @GET("api/v1/user/{userId}/memberships/{membershipId}/activity")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLHistorical>> getHistoricalSubscribe(@Header("Authorization") String str, @Path("userId") String str2, @Path("membershipId") Long l);

    @GET("api/v1/restaurant/{restaurantId}/programs?restaurants=false")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLProgram>> getListProgram(@Header("Authorization") String str, @Path("restaurantId") String str2);

    @GET("api/v1/user/{userId}/restaurant/{restaurantId}/programs")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLProgram>> getListProgramByUser(@Header("Authorization") String str, @Path("userId") String str2, @Path("restaurantId") String str3);

    @GET("api/v1/user/{userId}/rewards/available")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLReward>> getListRewardByUser(@Header("Authorization") String str, @Path("userId") String str2, @Query("restaurantId") String str3);

    @GET("api/v1/user/{userId}/memberships/active")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLMemberShip>> getListSubscribesActiveByUser(@Header("Authorization") String str, @Path("userId") String str2);

    @GET("api/v1/user/{userId}/memberships")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLMemberShip>> getListSubscribesByUser(@Header("Authorization") String str, @Path("userId") String str2, @Query("restaurantId") String str3);

    @POST("api/v1/user/{userId}/restaurants/{restaurantId}/programs/enroll")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<List<CLMemberShip>> postSubscribes(@Header("Authorization") String str, @Path("userId") String str2, @Path("restaurantId") String str3, @Body CLPostSubscribe cLPostSubscribe);

    @PUT("api/v1/user/{userId}/restaurants/{restaurantId}/favorite")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<Object> putChangeRestaurant(@Header("Authorization") String str, @Path("userId") String str2, @Path("restaurantId") String str3, @Body String str4);

    @GET("api/v1/user/{userId}/restaurants/{restaurantId}/favorite")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<CLFavoriteRestaurant> verifyFavoriteRestaurant(@Header("Authorization") String str, @Path("userId") String str2, @Path("restaurantId") String str3);

}
