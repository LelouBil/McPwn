package net.leloubil.mcpwn.async;

import android.os.AsyncTask;
import lombok.experimental.UtilityClass;

import java.util.Timer;
import java.util.TimerTask;

@UtilityClass
public class TimeLimit {

    public <T> void run(Runnable r, long limit, CallBackPair<T, Class> callbacks) {
        Timer t = new Timer();
        AsyncTask<CallBackPair<T, Class>, Void, Void> task = new AsyncTask<CallBackPair<T, Class>, Void, Void>() {
            @SafeVarargs
            @Override
            protected final Void doInBackground(CallBackPair<T, Class>... callBackPairs) {
                t.cancel();
                r.run();
                return null;
            }
        };
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                t.cancel();
                task.cancel(false);
                callbacks.disable();
                callbacks.failure(TimeLimit.class);
            }
        }, limit);

        //noinspection unchecked
        task.execute(callbacks);
    }
}
