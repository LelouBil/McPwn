package net.mcdonalds;

import net.leloubil.mcpwn.mcapi.AppToken;
import net.leloubil.mcpwn.mcapi.UserToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IOAuth {
    @FormUrlEncoded
    @POST("/oauth/token")
    @Headers({"Cache-Control: no-cache, no-store, must-revalidate"})
    Call<AppToken> getAppToken(@Field("client_id") String str, @Field("client_secret") String str2, @Field("grant_type") String str3);

    @FormUrlEncoded
    @POST("/oauth/token")
    @Headers({"Cache-Control: no-cache, no-store, must-revalidate"})
    Call<UserToken> logUser(@Field("client_id") String str, @Field("client_secret") String str2, @Field("grant_type") String str3, @Field("code") String str4, @Field("redirect_uri") String str5);

    @FormUrlEncoded
    @POST("/oauth/token")
    @Headers({"Cache-Control: no-cache, no-store, must-revalidate"})
    Call<UserToken> refreshUserToken(@Field("client_id") String str, @Field("client_secret") String str2, @Field("grant_type") String str3, @Field("refresh_token") String str4);
}
