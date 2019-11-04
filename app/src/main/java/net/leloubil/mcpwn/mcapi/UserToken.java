package net.leloubil.mcpwn.mcapi;

import android.util.Log;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.function.Consumer;

@Getter
@Setter
@ToString(callSuper = true)
public class UserToken extends Token {

    private String refreshToken;

    public UserToken(String token, int expiresIn) {
        super(token, expiresIn);
    }

    public UserToken(String token, String refreshToken, int expiresIn) {
        super(token, expiresIn);
        this.refreshToken = refreshToken;
    }

    private UserToken() {

    }

    static void refresh(String refreshToken, Consumer<UserToken> consumer) {
        UserToken t = new UserToken();
        t.refreshToken = refreshToken;
        t.forceUpdate(consumer);
    }

    public void getToken(Consumer<String> consumer) {
        if (isValid()) consumer.accept(super.getToken());
        else forceUpdate((s) -> consumer.accept(s.getToken()));
    }

    private void forceUpdate(Consumer<UserToken> consumer) {
        Log.d("Update", "token for " + this.refreshToken);
        McAPI.getIoAuth().refreshUserToken(McAPI.client_id, McAPI.client_secret, "refresh_token", refreshToken).enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                UserToken.this.token = response.body().token;
                Log.d("Update", "token for " + UserToken.this.refreshToken + " is finally " + UserToken.this.token);
                UserToken.this.expiresIn = response.body().expiresIn;
                UserToken.this.setTime();
                consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {

            }
        });

    }


    @Override
    protected void forceUpdate() {
        this.forceUpdate(i -> {
        });
    }
}
