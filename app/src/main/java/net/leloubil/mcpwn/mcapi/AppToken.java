package net.leloubil.mcpwn.mcapi;

import android.util.Log;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import net.leloubil.mcpwn.async.CallBackPair;
import net.leloubil.mcpwn.async.McPwnAPICallback;
import net.leloubil.mcpwn.async.TimeLimit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.concurrent.TimeUnit;

@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppToken extends Token {


    private static AppToken current;


    public AppToken(String token, int expiresIn) {
        super(token, expiresIn);
    }

    private AppToken() {
        super();
    }

    public static AppToken get(McPwnAPICallback<AppToken> callback) {
        if (current == null) {
            current = new AppToken();
            current.forceUpdate(callback);
            Log.i("act", "just forced like now");
        } else current.update(callback);
        return current;
    }

    public static void init(CallBackPair<Class, Class> callbacks) {
        TimeLimit.run(() -> {
            AppToken.get(new McPwnAPICallback<AppToken>(callbacks.getContext()).onResponse((a, b, c) -> callbacks.success(AppToken.class)).onFailure((a, b, c) -> callbacks.failure(AppToken.class)));
        }, TimeUnit.SECONDS.toMillis(15), callbacks);

    }

    private static boolean hasToken() {
        return current.token != null;
    }

    private void forceUpdate(McPwnAPICallback<AppToken> callback) {
        McAPI.getIoAuth().getAppToken(McAPI.client_id, McAPI.client_secret, "client_credentials").enqueue(new Callback<AppToken>() {
            @Override
            public void onResponse(Call<AppToken> call, Response<AppToken> response) {
                current = response.body();
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<AppToken> call, Throwable t) {
                t.printStackTrace();
                Log.d("heya", "onFailure: machintruc");
            }
        });
    }

    private boolean update(McPwnAPICallback<AppToken> callback) {
        if (isValid()) return false;
        forceUpdate(callback);
        setTime();
        return true;
    }

    @Override
    protected void forceUpdate() {
        McAPI.getIoAuth().getAppToken(McAPI.client_id, McAPI.client_secret, "client_credentials").enqueue(new Callback<AppToken>() {
            @Override
            public void onResponse(Call<AppToken> call, Response<AppToken> response) {
                current = response.body();
            }

            @Override
            public void onFailure(Call<AppToken> call, Throwable t) {
                t.printStackTrace();
                Log.d("heya", "onFailure: machintruc");
            }
        });

    }

}
