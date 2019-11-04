package net.leloubil.mcpwn.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;
import lombok.NonNull;
import net.leloubil.mcpwn.CustomExceptionHandler;
import net.leloubil.mcpwn.DataManagement;
import net.leloubil.mcpwn.R;
import net.leloubil.mcpwn.async.CallBackPair;
import net.leloubil.mcpwn.fragments.LoginFragment;
import net.leloubil.mcpwn.fragments.StatusFragment;
import net.leloubil.mcpwn.fragments.UserDisplayFragment;
import net.leloubil.mcpwn.fragments.UserInfoFragment;
import net.leloubil.mcpwn.mcapi.AppToken;
import net.leloubil.mcpwn.mcapi.McAPI;
import net.leloubil.mcpwn.mcapi.UserData;
import net.leloubil.mcpwn.mcapi.UserToken;

@SuppressWarnings("NullableProblems")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean failure = false;


    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private CallBackPair<Class, Class> callbacks = CallBackPair.duo(this, this::loadFinish, this::loadfail);

    public void loadFragment(Fragment f) {
        long start = System.currentTimeMillis();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).addToBackStack("back").commit();
        long end = System.currentTimeMillis();
        Log.d("Time limiterdsdf ", "ddd: fragment load " + (end - start) + "ms");
    }

    private void loadFinish(Class aClass) {
        if (failure) return;
        if (aClass.equals(McAPI.class)) {
            Log.d("Loading", "Loading APPTOKEN");
            AppToken.init(callbacks);
        } else if (aClass.equals(AppToken.class)) {
            Log.d("Loading", "Loading DataManagement");
            DataManagement.init(callbacks);
        } else if (aClass.equals(DataManagement.class)) {
            Log.d("Loading", "Loading finished");
            this.finishedLoading();
        }
    }

    private void finishedLoading() {
        //todo faire l'ui bien
        loadFragment(new StatusFragment());  //load root Fragment
        findViewById(R.id.MainLoading).setVisibility(View.GONE);
        runOnUiThread(() -> Toast.makeText(this, "Loading finished !", Toast.LENGTH_SHORT).show());

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        DataManagement.saveData(this);
    }

    private void loadfail(Class cls) {
        failure = true;
        runOnUiThread(() -> Toast.makeText(this, "Loading failed for class : " + cls.getCanonicalName(), Toast.LENGTH_LONG).show());
        //todo modifier l'ui
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sentry.init("https://f9707463e5ca47e192b3901e038b3671@sentry.io/1788402", new AndroidSentryClientFactory(this));
        if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof CustomExceptionHandler)) {
            Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());
        }

        setContentView(R.layout.activity_main);
        checkForUpdate();
        findViewById(R.id.MainLoading).setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
        init();
    }

    private void checkForUpdate() {
        AppUpdater appUpdater = new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.GITHUB)
                .setGitHubUserAndRepo("LelouBil", "McPwn")
                .setTitleOnUpdateAvailable("Mise a jour disponible")
                .setTitleOnUpdateNotAvailable("Aucune mises a jour disponible")
                .setButtonUpdate("Mettre a jour maintenant")
                .setIcon(R.drawable.ic_system_update_white_24dp) // Notification icon
                .setCancelable(false);
        appUpdater.start();
    }


    private void init() {
        Log.d("Loading", "Loading McAPI");
        McAPI.init(callbacks);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_ac_list:
                drawer.closeDrawer(GravityCompat.START);
                new Handler().postDelayed(() -> {
                    loadFragment(new UserDisplayFragment(MainActivity.this::onAccountAdd, MainActivity.this::onClick)); // your fragment transactions go here
                }, 200);

                break;
            case R.id.nav_status:
                drawer.closeDrawer(GravityCompat.START);
                new Handler().postDelayed(() -> {
                    loadFragment(new StatusFragment()); // your fragment transactions go here
                }, 200);
                break;
        }
        return true;
    }

    private void onAccountAdd(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment(this::onCodeReceive)).commit();

    }

    private void onCodeReceive(String s) {
        UserData.onLoginLocation(this, s, this::onDataReceived);
    }

    private void onDataReceived(UserToken s) {
        UserData.createUser(s, this);
        runOnUiThread(() -> Toast.makeText(this, "User added !", Toast.LENGTH_SHORT).show());
        loadFragment(new UserDisplayFragment(this::onAccountAdd, MainActivity.this::onClick));
    }

    private void onClick(UserData data) {
        loadFragment(UserInfoFragment.newInstance(data));
    }
}
