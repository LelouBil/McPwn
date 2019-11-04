package net.leloubil.mcpwn.mcapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.leloubil.mcpwn.DataManagement;
import net.leloubil.mcpwn.async.CallBackPair;
import net.leloubil.mcpwn.carvinglabs.CLMemberShip;
import net.mcdonalds.OrderGet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserData implements Serializable {


    private static final String barcode_url = "/api/customer/%s/barcode";
    private UserInfo info;
    UserToken userToken;
    @Getter
    private CLMemberShip memberShip;
    private List<Consumer<UserInfo>> onInfoUpdate = new ArrayList<>();

    private UserData() {

    }

    private UserData(UserToken userToken, Consumer<UserData> dataConsumer) {
        this.userToken = userToken;
        this.getInfo((i) -> {
            this.info = i;
            refreshMembership(m -> {
                this.memberShip = m;
                dataConsumer.accept(this);
            });
        });
    }

    public static void fromRefresh(String token, CallBackPair<UserData, Class> cons) {
        UserToken.refresh(token, in -> {
            UserData d = new UserData();
            d.userToken = in;
            if (DataManagement.hasInfo(token, cons.getContext())) {
                d.info = DataManagement.cachedInfo(token, cons.getContext());
                d.updateLater();
                d.refreshMembership(m -> {
                    d.memberShip = m;
                    cons.success(d);
                });
            } else {
                d.getInfo(i -> {
                    d.info = i;
                    d.refreshMembership(m -> {
                        d.memberShip = m;
                        cons.success(d);
                    });
                });
            }
        });

    }

    public static void createUser(UserToken s, Context c) {
        UserData d = new UserData(s, (da) -> DataManagement.addUser(da, c));
    }

    public static void onLoginLocation(Context c, String code, Consumer<UserToken> oncode) {
        McAPI.getIoAuth().logUser(McAPI.client_id, McAPI.client_secret, "authorization_code", code, "mcdo:login").enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                oncode.accept(response.body());

            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {

            }
        });
    }

    private void updateLater() {
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                UserData.this.getInfo(UserData.this::updateInfo);
                return null;
            }
        };
        task.execute();
    }

    private void updateInfo(UserInfo i) {
        this.info = i;
        onInfoUpdate.forEach(c -> c.accept(i));
        onInfoUpdate.clear();
    }

    public void syncInfo(Consumer<UserInfo> i) {
        if (info != null) {
            i.accept(info);
        } else onInfoUpdate.add(i);
    }

    private void getInfo(Consumer<UserInfo> infoMcPwnCallback) {
        McAPI.getUserAuth().retrieveUserInformations("Bearer " + userToken.getToken(), null).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                infoMcPwnCallback.accept(response.body());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    private void refreshMembership(Consumer<CLMemberShip> consumer) {
        getUserToken().getToken(t -> McAPI.getCarvinglabs().getListSubscribesActiveByUser("Bearer " + t, getInfo().getRef()).enqueue(new Callback<List<CLMemberShip>>() {
            @Override
            public void onResponse(Call<List<CLMemberShip>> call, Response<List<CLMemberShip>> response) {
                if (response.body() == null || response.body().size() == 0) consumer.accept(null);
                else consumer.accept(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<CLMemberShip>> call, Throwable t) {

            }
        }));
    }

    public void loadBarCode(ImageView viewById) {
        Glide.with(viewById)
                .load(new GlideUrl(McAPI.baseUrl + barcode_url.replace("%s", getInfo().getRef()), new LazyHeaders.Builder().setHeader("Authorization", "Bearer " + getUserToken().getToken()).build()))
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).signature(new ObjectKey(getInfo().getRef())))
                .into(viewById);
    }

    public void getOrders(Consumer<List<OrderGet>> consumer) {
        getUserToken().getToken(t -> McAPI.getUserAuth().retrieveOrders("Bearer " + t, getInfo().getRef(), "RG.ORDER.ITEMS").enqueue(new Callback<List<OrderGet>>() {
            @Override
            public void onResponse(Call<List<OrderGet>> call, Response<List<OrderGet>> response) {
                if (response.body() == null || response.body().size() == 0) consumer.accept(null);
                else consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderGet>> call, Throwable t) {

            }
        }));
    }
}
